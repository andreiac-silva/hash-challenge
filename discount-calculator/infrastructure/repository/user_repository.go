package repository

import (
	"context"
	"discount-calculator/domain"
	"discount-calculator/internal/errors"
	"discount-calculator/internal/logger"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/bson/primitive"
	"go.mongodb.org/mongo-driver/mongo"
	"os"
)

func NewUserRepository(mongoClient *mongo.Client) UserRepository {
	return UserRepository{
		client:     mongoClient,
		database:   os.Getenv("MONGODB_DATABASE"),
		collection: os.Getenv("MONGODB_USER_COLLECTION"),
	}
}

type UserRepository struct {
	client     *mongo.Client
	database   string
	collection string
}

func (db UserRepository) FindOne(id string) (user domain.User, err error) {
	objId, err := primitive.ObjectIDFromHex(id)
	if err != nil {
		err = &errors.InvalidAttribute{Err: err, Message: "Invalid User Id"}
		logger.Logger.Errorw("error converting user id to ObjectID", "error", err.Error())
		return
	}

	result := db.client.Database(db.database).Collection(db.collection).
		FindOne(context.Background(), bson.M{"_id": bson.M{"$eq": objId}})

	if result.Err() != nil {
		switch result.Err() {
		case mongo.ErrNoDocuments:
			err = &errors.EntityNotFound{Err: result.Err(), Message: "User not found"}
			logger.Logger.Errorw("User not found", "id", id, "error", err.Error())
			return
		default:
			err = &errors.DatabaseConnectionError{Err: result.Err()}
			logger.Logger.Errorw("Something went wrong finding user", "error", err.Error())
			return
		}
	}

	err = result.Decode(&user)
	if err != nil {
		logger.Logger.Errorw("Error decoding domain", "error", err.Error())
		return
	}
	return
}
