package discount

import (
	"discount-calculator/domain/discount"
)

type UseCase interface {
	CalculateDiscount(userId, productId string) (*discount.Discount, error)
}
