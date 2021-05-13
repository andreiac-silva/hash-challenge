package domain

import "time"

type User struct {
	Id          string    `bson:"_id"`
	DateOfBirth time.Time `bson:"date_of_birth"`
}

func (u *User) IsBirthday(currentDate time.Time) bool {
	if u.DateOfBirth.IsZero() {
		return false
	}
	_, month, day := currentDate.Date()
	_, umonth, uday := u.DateOfBirth.Date()
	return month == umonth && day == uday
}
