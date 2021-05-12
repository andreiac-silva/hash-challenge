package domain

import "time"

type User struct {
	Id          string    `bson:"_id"`
	DateOfBirth time.Time `bson:"date_of_birth"`
}

func (u *User) IsBirthday() bool {
	if u.DateOfBirth.IsZero() {
		return false
	}
	_, month, day := time.Now().Date()
	_, umonth, uday := u.DateOfBirth.Date()
	return month == umonth && day == uday
}
