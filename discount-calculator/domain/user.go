package domain

import "time"

type User struct {
	Id          string    `bson:"_id"`
	DateOfBirth time.Time `bson:"date_of_birth"`
}

func (u *User) IsBirthday(currentDate time.Time) bool {
	birthdayUTC := u.getUTCDateOfBirth()
	if birthdayUTC.IsZero() {
		return false
	}
	_, month, day := currentDate.Date()
	_, umonth, uday := birthdayUTC.Date()
	return month == umonth && day == uday
}

func (u *User) getUTCDateOfBirth() time.Time {
	return u.DateOfBirth.UTC()
}
