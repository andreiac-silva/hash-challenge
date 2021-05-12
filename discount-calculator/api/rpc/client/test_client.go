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
		UserId:    "d25095bc-b2cc-11eb-b051-f34c68e013d3",
		ProductId: "d84c1d10-b2cc-11eb-8b64-a7ff55ffa104",
	}

	res, err := cc.Discount(context.Background(), req)

	if err != nil {
		log.Fatalf("something wnte wrong: %v", err)
	}
	log.Printf("My first gRPC request, returned: %v", res)
}
