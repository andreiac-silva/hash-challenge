package discount

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

const (
	percentage   = float32(10)
	valueInCents = int64(1000)
)

func TestGetPercentage(t *testing.T) {
	discount := Discount{percentage: percentage}
	actual := discount.GetPercentage()
	assert.Equal(t, actual, percentage)
}

func TestGetValueInCents(t *testing.T) {
	discount := Discount{valueInCents: valueInCents}
	actual := discount.GetValueInCents()
	assert.Equal(t, actual, valueInCents)
}

func TestCalculate(t *testing.T) {
	cases := []struct {
		priceInCents       int64
		discountInCents    int64
		discountPercentage float32
	}{
		{
			priceInCents:       100,
			discountInCents:    5,
			discountPercentage: 5,
		},
		{
			priceInCents:       1000,
			discountInCents:    500,
			discountPercentage: 50,
		},
		{
			priceInCents:       2000,
			discountInCents:    1400,
			discountPercentage: 70,
		},
	}

	for _, c := range cases {
		d := Discount{}
		d.calculate(c.discountPercentage, c.priceInCents)
		assert.Equal(t, d.percentage, c.discountPercentage)
		assert.Equal(t, d.valueInCents, c.discountInCents)
	}
}
