package usecase

import (
	"discount-calculator/usecase/discount"
	"discount-calculator/usecase/product"
	"discount-calculator/usecase/user"
	"github.com/google/wire"
)

var Provider = wire.NewSet(
	user.NewService,
	product.NewService,
	discount.NewService,
	wire.Bind(new(user.UseCase), new(*user.Service)),
	wire.Bind(new(product.UseCase), new(*product.Service)),
	wire.Bind(new(discount.UseCase), new(*discount.Service)),
)
