package api

import (
	api "discount-calculator/api/rpc/server"
	"github.com/google/wire"
)

var Provider = wire.NewSet(api.NewServer)
