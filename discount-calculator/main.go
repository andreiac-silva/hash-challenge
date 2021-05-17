package main

import (
	"discount-calculator/pkg/logger"
	"github.com/joho/godotenv"
)

func init() {
	err := godotenv.Load()
	if err != nil {
		panic("Error loading application: cannot load environment variables: " + err.Error())
	}
	logger.SetupLogger()
}

func main() {
	logger.Logger.Info("Starting discount-calculator...")
	app := SetupApplication()
	must(app.StartServer())
}

func must(err error) {
	if err != nil {
		panic(err)
	}
}
