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

func NewProductRepository(mongoClient *mongo.Client) ProductRepository {
	return ProductRepository{
		client:     mongoClient,
		database:   os.Getenv("MONGODB_DATABASE"),
		collection: os.Getenv("MONGODB_PRODUCT_COLLECTION"),
	}
}

type ProductRepository struct {
	client     *mongo.Client
	database   string
	collection string
}

func (db ProductRepository) FindOne(id string) (product domain.Product, err error) {
	objId, err := db.toObjectID(id)
	if err != nil {
		return
	}

	result := db.client.Database(db.database).Collection(db.collection).
		FindOne(context.Background(), bson.M{"_id": bson.M{"$eq": objId}})

	if result.Err() != nil {
		switch result.Err() {
		case mongo.ErrNoDocuments:
			err = &errors.EntityNotFound{Err: result.Err(), Message: "Product not found"}
			logger.Logger.Errorw("Product not found", "id", id, "error", err.Error())
			return
		default:
			err = &errors.DatabaseConnectionError{Err: result.Err()}
			logger.Logger.Errorw("Something went wrong finding product", "error", err.Error())
			return
		}
	}

	err = result.Decode(&product)
	if err != nil {
		logger.Logger.Errorw("Error decoding domain", "error", err.Error())
		return
	}
	return
}

func (db ProductRepository) toObjectID(id string) (objID primitive.ObjectID, err error) {
	if id == "" {
		logger.Logger.Errorw("product id is required but it was not informed")
		err = &errors.MissingAttribute{Message: "Missing Product Id"}
		return
	}
	objId, err := primitive.ObjectIDFromHex(id)
	if err != nil {
		logger.Logger.Errorw("error converting product id to ObjectID", "error", err.Error())
		err = &errors.InvalidAttribute{Err: err, Message: "Invalid Product Id"}
		return
	}
	return objId, nil
}
