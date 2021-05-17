package mongodb

import (
	"discount-calculator/pkg/logger"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
	"golang.org/x/net/context"
	"os"
	"strconv"
	"time"
)

func NewMongoClient() *mongo.Client {
	conn := os.Getenv("MONGODB_CONNECTION_URL")
	user := os.Getenv("MONGODB_USERNAME")
	passwd := os.Getenv("MONGODB_PASSWORD")

	opt := options.Client().ApplyURI(conn)
	if user != "" && passwd != "" {
		opt.SetAuth(options.Credential{
			Username: user,
			Password: passwd,
		})
	}

	timeout := os.Getenv("MONGODB_SECONDS_TIMEOUT")
	i, _ := strconv.Atoi(timeout)
	ctx, _ := context.WithTimeout(context.Background(), time.Duration(i)*time.Second)

	cli, err := mongo.Connect(ctx, opt)
	if err != nil {
		logger.Logger.Fatalw("Error connecting on database", "error", err.Error())
	}

	err = cli.Ping(ctx, nil)
	if err != nil {
		logger.Logger.Fatalw("Ping has failed", "error", err.Error())
	}

	return cli
}
