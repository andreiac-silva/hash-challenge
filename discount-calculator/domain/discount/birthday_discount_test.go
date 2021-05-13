package discount

import (
	"discount-calculator/domain"
	"github.com/stretchr/testify/assert"
	"testing"
	"time"
)

var today = time.Now()

func TestApplyWhenIsUserBirthday(t *testing.T) {
	user := domain.User{
		DateOfBirth: today,
	}
	bthDiscount := BirthdayDiscount{
		PriceInCents: 1000,
		CurrentDate:  today,
		User:         user,
	}

	bthDiscount.Apply()

	discount := bthDiscount.GetDiscount()
	assert.Equal(t, discount.GetPercentage(), float32(birthdayPercentageDiscount))
	assert.Equal(t, discount.GetValueInCents(), int64(50))
}

func TestApplyWhenIsNotUserBirthday(t *testing.T) {
	user := domain.User{
		DateOfBirth: time.Now().AddDate(0, 0, 1),
	}
	bthDiscount := BirthdayDiscount{
		PriceInCents: 1000,
		CurrentDate:  today,
		User:         user,
	}

	bthDiscount.Apply()

	discount := bthDiscount.GetDiscount()
	assert.Equal(t, discount.GetPercentage(), float32(0))
	assert.Equal(t, discount.GetValueInCents(), int64(0))
}

func TestGetBirthdayDiscount(t *testing.T) {
	bfDiscount := BirthdayDiscount{
		discount: Discount{
			percentage:   birthdayPercentageDiscount,
			valueInCents: 2000,
		},
	}

	discount := bfDiscount.GetDiscount()

	assert.Equal(t, discount.percentage, float32(birthdayPercentageDiscount))
	assert.Equal(t, discount.valueInCents, int64(2000))
}
