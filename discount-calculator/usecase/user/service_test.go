package user

import (
	"discount-calculator/domain"
	"discount-calculator/pkg/errors"
	mock "discount-calculator/test/mock/user"
	"github.com/stretchr/testify/assert"
	"testing"
)

const (
	dummyUsrId = "58b89882-b770-11eb-a451-2f05541bdc4e"
	findOne    = "FindOne"
)

func TestNewService(t *testing.T) {
	repo := new(mock.Repository)

	service := NewService(repo)

	assert.NotNil(t, service)
	assert.NotNil(t, service.repo)
	assert.Equal(t, service.repo, repo)
}

func TestGetUserSuccess(t *testing.T) {
	usr := domain.User{}

	repo := new(mock.Repository)
	service := NewService(repo)

	repo.On(findOne, dummyUsrId).Return(usr, nil)
	returned, err := service.GetUser(dummyUsrId)

	assert.Nil(t, err)
	assert.NotNil(t, returned)
}

func TestGetUserErr(t *testing.T) {
	repo := new(mock.Repository)
	service := NewService(repo)

	repo.On(findOne, dummyUsrId).Return(nil, &errors.EntityNotFound{})
	_, err := service.GetUser(dummyUsrId)

	assert.NotNil(t, err)
}
