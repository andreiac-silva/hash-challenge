package discount

import "time"

const (
	blackFridayPercentageDiscount = 10
	blackFridayDay                = 25
	blackFridayMonth              = time.November
)

type BlackFridayDiscount struct {
	PriceInCents int64
	CurrentDate  time.Time
	discount     Discount
}

func (b *BlackFridayDiscount) Apply() {
	if b.isBlackFriday() {
		b.discount.calculate(blackFridayPercentageDiscount, b.PriceInCents)
	}
}

func (b *BlackFridayDiscount) GetDiscount() Discount {
	return b.discount
}

func (b *BlackFridayDiscount) isBlackFriday() bool {
	_, currentMonth, currentDay := b.CurrentDate.Date()
	return blackFridayMonth == currentMonth && blackFridayDay == currentDay
}
