package discount

import (
	"discount-calculator/domain"
	"time"
)

const birthdayPercentageDiscount = 5

type BirthdayDiscount struct {
	PriceInCents int64
	CurrentDate  time.Time
	User         domain.User
	discount     Discount
}

func (b *BirthdayDiscount) Apply() {
	if b.User.IsBirthday(b.CurrentDate) {
		b.discount.calculate(birthdayPercentageDiscount, b.PriceInCents)
	}
}

func (b *BirthdayDiscount) GetDiscount() Discount {
	return b.discount
}
