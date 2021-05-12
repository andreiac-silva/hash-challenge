package pkg

import (
	api "discount-calculator/api/rpc/server"
	"google.golang.org/grpc"
	"net"
)

type Application struct {
	Listener net.Listener
	Server   *grpc.Server
	RpcImpl  *api.Server
}

func NewListener() net.Listener {
	lis, err := net.Listen("tcp", "0.0.0.0:50051")
	if err != nil {
		panic("Error starting the application: it is not possible to listen tcp on default port")
	}
	return lis
}

func NewGrpcServer() *grpc.Server {
	return grpc.NewServer()
}

func (a Application) StartServer() error {
	return a.Server.Serve(a.Listener)
}
