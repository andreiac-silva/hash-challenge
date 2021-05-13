package domain

import (
	"github.com/stretchr/testify/assert"
	"testing"
	"time"
)

var today = time.Now()

func TestIsBirthdayWhenItIsNot(t *testing.T) {
	user := User{
		DateOfBirth: time.Now().AddDate(0, 0, 1),
	}
	assert.False(t, user.IsBirthday(today))
}

func TestIsBirthdayWhenItIs(t *testing.T) {
	user := User{
		DateOfBirth: today,
	}
	assert.True(t, user.IsBirthday(today))
}
