package product

import (
	"discount-calculator/domain"
)

type Reader interface {
	FindOne(id string) (domain.Product, error)
}

type Repository interface {
	Reader
}

type UseCase interface {
	GetProduct(id string) (domain.Product, error)
}
