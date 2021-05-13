package discount

import (
	"discount-calculator/domain/discount"
	"time"
)

type UseCase interface {
	CalculateDiscount(currentDate time.Time, userId, productId string) (*discount.Discount, error)
}
