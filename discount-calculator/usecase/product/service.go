package product

import (
	"discount-calculator/domain"
)

func NewService(repo Repository) *Service {
	return &Service{repo: repo}
}

type Service struct {
	repo Repository
}

func (uc *Service) GetProduct(id string) (domain.Product, error) {
	return uc.repo.FindOne(id)
}
