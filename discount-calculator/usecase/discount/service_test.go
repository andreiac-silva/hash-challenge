package discount

import (
	"discount-calculator/domain"
	"discount-calculator/pkg/errors"
	"discount-calculator/pkg/logger"
	product "discount-calculator/test/mock/product"
	user "discount-calculator/test/mock/user"
	"github.com/stretchr/testify/assert"
	"testing"
	"time"
)

var blackFridayDate = time.Date(2021, time.November, 25, 10, 0, 0, 0, time.UTC)
var nonBlackFridayDate = time.Date(2021, time.May, 12, 10, 0, 0, 0, time.UTC)

const (
	dummyUsrId                    = "78bj7882-b880-11eb-a451-2f05541bdc4f"
	dummyPrdId                    = "58b89882-b770-11eb-a451-2f05541bdc4e"
	blackFridayPercentageDiscount = float32(10)
	birthdayPercentageDiscount    = float32(5)
	getProduct                    = "GetProduct"
	getUser                       = "GetUser"
)

func init() {
	logger.SetupLogger()
}

func TestNewService(t *testing.T) {
	productService := new(product.UseCase)
	userService := new(user.UseCase)

	service := NewService(productService, userService)

	assert.NotNil(t, service)
	assert.NotNil(t, service.ucProduct)
	assert.Equal(t, service.ucProduct, productService)
	assert.NotNil(t, service.ucUser)
	assert.Equal(t, service.ucUser, userService)
}

func TestCalculateDiscountWhenUserIsNotInformedAndIsBlackFriday(t *testing.T) {
	emptyUserId := ""
	productService := new(product.UseCase)
	userService := new(user.UseCase)
	service := NewService(productService, userService)

	productService.On(getProduct, dummyPrdId).Return(createDefaultProduct(), nil)

	discount, err := service.CalculateDiscount(blackFridayDate, emptyUserId, dummyPrdId)

	assert.Nil(t, err)
	assert.NotNil(t, discount)
	assert.Equal(t, discount.GetPercentage(), blackFridayPercentageDiscount)
	assert.Equal(t, discount.GetValueInCents(), int64(100))
}

func TestCalculateDiscountWhenUserIsNotInformedAndIsNotBlackFriday(t *testing.T) {
	emptyUserId := ""
	productService := new(product.UseCase)
	userService := new(user.UseCase)
	service := NewService(productService, userService)

	productService.On(getProduct, dummyPrdId).Return(createDefaultProduct(), nil)

	discount, err := service.CalculateDiscount(nonBlackFridayDate, emptyUserId, dummyPrdId)

	assert.Nil(t, err)
	assert.NotNil(t, discount)
	assert.Equal(t, discount.GetPercentage(), float32(0))
	assert.Equal(t, discount.GetValueInCents(), int64(0))
}

func TestCalculateDiscountWhenUserIsInformedAndIsNotBirthdayNorBlackFriday(t *testing.T) {
	productService := new(product.UseCase)
	userService := new(user.UseCase)
	service := NewService(productService, userService)

	usr := domain.User{Id: dummyUsrId, DateOfBirth: nonBlackFridayDate.AddDate(0, 0, 3)}

	productService.On(getProduct, dummyPrdId).Return(createDefaultProduct(), nil)
	userService.On(getUser, dummyUsrId).Return(usr, nil)

	discount, err := service.CalculateDiscount(nonBlackFridayDate, dummyUsrId, dummyPrdId)

	assert.Nil(t, err)
	assert.NotNil(t, discount)
	assert.Equal(t, discount.GetPercentage(), float32(0))
	assert.Equal(t, discount.GetValueInCents(), int64(0))
}

func TestCalculateDiscountWhenUserIsInformedAndIsBirthdayButNotBlackFriday(t *testing.T) {
	productService := new(product.UseCase)
	userService := new(user.UseCase)
	service := NewService(productService, userService)

	usr := domain.User{Id: dummyUsrId, DateOfBirth: nonBlackFridayDate}

	productService.On(getProduct, dummyPrdId).Return(createDefaultProduct(), nil)
	userService.On(getUser, dummyUsrId).Return(usr, nil)

	discount, err := service.CalculateDiscount(nonBlackFridayDate, dummyUsrId, dummyPrdId)

	assert.Nil(t, err)
	assert.NotNil(t, discount)
	assert.Equal(t, discount.GetPercentage(), birthdayPercentageDiscount)
	assert.Equal(t, discount.GetValueInCents(), int64(50))
}

func TestCalculateDiscountWhenUserIsInformedAndIsBirthdayAndBlackFriday(t *testing.T) {
	productService := new(product.UseCase)
	userService := new(user.UseCase)
	service := NewService(productService, userService)

	usr := domain.User{Id: dummyUsrId, DateOfBirth: blackFridayDate}

	productService.On(getProduct, dummyPrdId).Return(createDefaultProduct(), nil)
	userService.On(getUser, dummyUsrId).Return(usr, nil)

	discount, err := service.CalculateDiscount(blackFridayDate, dummyUsrId, dummyPrdId)

	assert.Nil(t, err)
	assert.NotNil(t, discount)
	assert.Equal(t, discount.GetPercentage(), blackFridayPercentageDiscount)
	assert.Equal(t, discount.GetValueInCents(), int64(100))
}

func TestCalculateDiscountWhenUserIsInformedButNotFound(t *testing.T) {
	productService := new(product.UseCase)
	userService := new(user.UseCase)
	service := NewService(productService, userService)

	productService.On(getProduct, dummyPrdId).Return(createDefaultProduct(), nil)
	userService.On(getUser, dummyUsrId).Return(nil, &errors.EntityNotFound{})

	discount, err := service.CalculateDiscount(blackFridayDate, dummyUsrId, dummyPrdId)

	assert.NotNil(t, err)
	assert.Nil(t, discount)
}

func TestCalculateDiscountWhenProductIsNotFound(t *testing.T) {
	productService := new(product.UseCase)
	userService := new(user.UseCase)
	service := NewService(productService, userService)

	productService.On(getProduct, dummyPrdId).Return(nil, &errors.EntityNotFound{})

	discount, err := service.CalculateDiscount(blackFridayDate, dummyUsrId, dummyPrdId)

	assert.NotNil(t, err)
	assert.Nil(t, discount)
}

func createDefaultProduct() domain.Product {
	return domain.Product{
		Id:           dummyPrdId,
		PriceInCents: 1000,
	}
}
