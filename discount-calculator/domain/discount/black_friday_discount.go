package discount

import "time"

const blackFridayPercentageDiscount = 10

type BlackFridayDiscount struct {
	PriceInCents int64
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
	_, month, day := time.Now().Date()
	return month == 11 && day == 25
}
