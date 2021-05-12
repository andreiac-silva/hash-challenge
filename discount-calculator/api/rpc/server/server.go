package api

import (
	"context"
	pb "discount-calculator/api/rpc/protocol"
	"discount-calculator/usecase/discount"
	"google.golang.org/grpc"
)

func NewServer(ucDiscount discount.UseCase, grpcSrv *grpc.Server) *Server {
	srv := &Server{
		ucDiscount: ucDiscount,
		grpcSrv:    grpcSrv,
	}
	pb.RegisterDiscountServiceServer(grpcSrv, srv)
	return srv
}

type Server struct {
	ucDiscount discount.UseCase
	grpcSrv    *grpc.Server
}

func (s *Server) Discount(cxt context.Context, req *pb.DiscountRequest) (*pb.DiscountResponse, error) {
	d, _ := s.ucDiscount.CalculateDiscount(req.UserId, req.ProductId)

	return &pb.DiscountResponse{
		Percentage:   d.GetPercentage(),
		ValueInCents: d.GetValueInCents(),
	}, nil
}
