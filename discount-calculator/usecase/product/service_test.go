package product

import (
	"discount-calculator/domain"
	"discount-calculator/pkg/errors"
	mock "discount-calculator/test/mock/product"
	"github.com/stretchr/testify/assert"
	"testing"
)

const (
	dummyPrdId = "58b89882-b770-11eb-a451-2f05541bdc4e"
	findOne    = "FindOne"
)

func TestNewService(t *testing.T) {
	repo := new(mock.Repository)
	service := NewService(repo)

	assert.NotNil(t, service)
	assert.NotNil(t, service.repo)
	assert.Equal(t, service.repo, repo)
}

func TestGetProductSuccess(t *testing.T) {
	product := domain.Product{}

	repo := new(mock.Repository)
	service := NewService(repo)

	repo.On(findOne, dummyPrdId).Return(product, nil)
	returned, err := service.GetProduct(dummyPrdId)

	assert.Nil(t, err)
	assert.NotNil(t, returned)
}

func TestGetProductErr(t *testing.T) {
	repo := new(mock.Repository)
	service := NewService(repo)

	repo.On(findOne, dummyPrdId).Return(nil, &errors.EntityNotFound{})
	_, err := service.GetProduct(dummyPrdId)

	assert.NotNil(t, err)
}
