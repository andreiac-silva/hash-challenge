package user

import (
	"discount-calculator/domain"
)

func NewService(repo Repository) *Service {
	return &Service{repo: repo}
}

type Service struct {
	repo Repository
}

func (uc *Service) GetUser(id string) (domain.User, error) {
	return uc.repo.FindOne(id)
}
