package main

import (
	"context"
	protocol2 "discount-calculator/api/rpc/protocol"
	"google.golang.org/grpc"
	"log"
)

func main() {
	conn, _ := grpc.Dial("localhost:50051", grpc.WithInsecure())

	defer conn.Close()

	c := protocol2.NewDiscountServiceClient(conn)

	// Unary
	unary(c)
}

func unary(cc protocol2.DiscountServiceClient) {
	req := &protocol2.DiscountRequest{
		UserId:    "609c762cbd24751085f35800",
		ProductId: "609c74c0bd24751085f357c9",
	}

	res, err := cc.Discount(context.Background(), req)

	if err != nil {
		log.Fatalf("something wnte wrong: %v", err)
	}
	log.Printf("My first gRPC request, returned: %v", res)
}
