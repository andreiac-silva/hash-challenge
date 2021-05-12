//+build wireinject

package main

import (
	"discount-calculator/pkg"
	"github.com/google/wire"
)

func SetupApplication() (pkg.Application) {
	wire.Build(pkg.Container)
	return pkg.Application{}
}
