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
		UserId:    "",
		ProductId: "",
	}

	res, err := cc.Discount(context.Background(), req)

	if err != nil {
		log.Fatalf("something went wrong: %v", err)
	}
	log.Printf("My first gRPC request, returned: %v", res)
}
