package discount

import (
	"github.com/stretchr/testify/assert"
	"math/rand"
	"testing"
	"time"
)

func TestApplyWhenIsNotBlackFriday(t *testing.T) {
	bfDiscount := BlackFridayDiscount{
		CurrentDate: time.Date(2020, time.Month(rand.Intn(12)), 2, 0, 0, 0, 0, time.UTC),
	}
	assert.Equal(t, bfDiscount.discount.GetPercentage(), float32(0))
	assert.Equal(t, bfDiscount.discount.GetValueInCents(), int64(0))
}

func TestApplyWhenIsBlackFriday(t *testing.T) {
	bfDiscount := BlackFridayDiscount{
		PriceInCents: 1000,
		CurrentDate:  time.Date(2020, time.November, 25, 0, 0, 0, 0, time.UTC),
	}

	bfDiscount.Apply()

	assert.Equal(t, bfDiscount.discount.GetPercentage(), float32(blackFridayPercentageDiscount))
	assert.Equal(t, bfDiscount.discount.GetValueInCents(), int64(100))
}

func TestIsBlackFridayWhenIsNotInBlackFriday(t *testing.T) {
	bfDiscount := BlackFridayDiscount{
		CurrentDate: time.Date(2020, time.Month(rand.Intn(12)), 2, 0, 0, 0, 0, time.UTC),
	}
	assert.False(t, bfDiscount.isBlackFriday())
}

func TestIsBlackFridayWhenIsInBlackFriday(t *testing.T) {
	bfDiscount := BlackFridayDiscount{
		CurrentDate: time.Date(2020, time.November, 25, 0, 0, 0, 0, time.UTC),
	}
	assert.True(t, bfDiscount.isBlackFriday())
}

func TestGetBlackFridayDiscount(t *testing.T) {
	bfDiscount := BlackFridayDiscount{
		discount: Discount{
			percentage:   blackFridayPercentageDiscount,
			valueInCents: 2000,
		},
	}

	discount := bfDiscount.GetDiscount()

	assert.Equal(t, discount.percentage, float32(blackFridayPercentageDiscount))
	assert.Equal(t, discount.valueInCents, int64(2000))
}
