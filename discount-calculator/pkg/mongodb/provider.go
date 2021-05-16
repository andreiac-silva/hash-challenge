package mongodb

import "github.com/google/wire"

var Provider = wire.NewSet(NewMongoClient)
