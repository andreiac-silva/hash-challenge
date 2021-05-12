package domain

type Product struct {
	Id           string `bson:"_id"`
	PriceInCents int64  `bson:"price_in_cents"`
}
