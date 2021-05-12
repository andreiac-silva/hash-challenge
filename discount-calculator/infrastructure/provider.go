package infrastructure

import (
	"discount-calculator/infrastructure/repository"
	"discount-calculator/usecase/product"
	"discount-calculator/usecase/user"
	"github.com/google/wire"
)

var Provider = wire.NewSet(
	repository.NewUserRepository,
	repository.NewProductRepository,
	wire.Bind(new(user.Repository), new(repository.UserRepository)),
	wire.Bind(new(product.Repository), new(repository.ProductRepository)),
)
