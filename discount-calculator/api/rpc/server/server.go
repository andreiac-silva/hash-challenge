package api

import (
	"context"
	pb "discount-calculator/api/rpc/protocol"
	"discount-calculator/internal/errors"
	"discount-calculator/usecase/discount"
	"google.golang.org/grpc"
	"google.golang.org/grpc/codes"
	"google.golang.org/grpc/status"
	"time"
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
	d, err := s.ucDiscount.CalculateDiscount(time.Now(), req.UserId, req.ProductId)

	if err != nil {
		return nil, s.handleErr(err)
	}

	return &pb.DiscountResponse{
		Percentage:   d.GetPercentage(),
		ValueInCents: d.GetValueInCents(),
	}, nil
}

func (s *Server) handleErr(err error) error {
	switch err.(type) {
	case *errors.InvalidAttribute, *errors.MissingAttribute:
		return status.Errorf(codes.InvalidArgument, err.Error())
	case *errors.EntityNotFound:
		return status.Errorf(codes.NotFound, err.Error())
	default:
		return status.Errorf(codes.Internal, err.Error())
	}
}
