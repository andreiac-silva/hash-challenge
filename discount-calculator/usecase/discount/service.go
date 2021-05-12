package discount

import (
	"discount-calculator/domain"
	"discount-calculator/domain/discount"
	"discount-calculator/internal/logger"
	"discount-calculator/usecase/product"
	"discount-calculator/usecase/user"
)

func NewService(ucProduct product.UseCase, ucUser user.UseCase) *Service {
	return &Service{
		ucProduct: ucProduct,
		ucUser:    ucUser,
	}
}

type Service struct {
	ucProduct product.UseCase
	ucUser    user.UseCase
}

func (uc Service) CalculateDiscount(userId, productId string) (*discount.Discount, error) {
	product, err := uc.ucProduct.GetProduct(productId)
	if err != nil {
		logger.Logger.Errorf("Something went wrong while looking for product with id %s", productId)
		return nil, err
	}

	var user domain.User
	if userId != "" {
		user, err = uc.ucUser.GetUser(userId)
		if err != nil {
			logger.Logger.Errorf("Something went wrong while looking for user with id %s", userId)
			return nil, err
		}
	}

	bthDiscount := discount.BirthdayDiscount{User: user, PriceInCents: product.PriceInCents}
	bfDiscount := discount.BlackFridayDiscount{PriceInCents: product.PriceInCents}
	return uc.applyAndSelectGreater(&bthDiscount, &bfDiscount), nil
}

func (uc Service) applyAndSelectGreater(discounts ...discount.Command) (maxDiscount *discount.Discount) {
	var max float32
	for i, discount := range discounts {
		discount.Apply()
		d := discount.GetDiscount()
		if i == 0 || max < d.GetPercentage() {
			max = d.GetPercentage()
			maxDiscount = &d
		}
	}
	return
}
