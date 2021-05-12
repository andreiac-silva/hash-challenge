package pkg

import (
	"discount-calculator/api"
	"discount-calculator/infrastructure"
	database "discount-calculator/internal/mongodb"
	"discount-calculator/usecase"
	"github.com/google/wire"
)

var Container = wire.NewSet(
	database.Provider,
	infrastructure.Provider,
	usecase.Provider,
	NewListener,
	NewGrpcServer,
	api.Provider,
	wire.Struct(new(Application), "*"),
)
