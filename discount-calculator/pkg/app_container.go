package pkg

import (
	"discount-calculator/api"
	"discount-calculator/infrastructure"
	"discount-calculator/pkg/mongodb"
	"discount-calculator/usecase"
	"github.com/google/wire"
)

var Container = wire.NewSet(
	mongodb.Provider,
	infrastructure.Provider,
	usecase.Provider,
	NewListener,
	NewGrpcServer,
	api.Provider,
	wire.Struct(new(Application), "*"),
)
