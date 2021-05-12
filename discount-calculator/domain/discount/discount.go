package discount

type Command interface {
	Apply()
	GetDiscount() Discount
}

type Discount struct {
	percentage   float32
	valueInCents int64
}

func (d *Discount) GetPercentage() float32 {
	return d.percentage
}

func (d *Discount) GetValueInCents() int64 {
	return d.valueInCents
}

func (d *Discount) calculate(percentage float32, priceInCents int64) {
	d.valueInCents = int64(float32(priceInCents)*percentage) / 100
	d.percentage = percentage
}
