package user

import (
	"discount-calculator/domain"
)

type Reader interface {
	FindOne(id string) (domain.User, error)
}

type Repository interface {
	Reader
}

type UseCase interface {
	GetUser(id string) (domain.User, error)
}
