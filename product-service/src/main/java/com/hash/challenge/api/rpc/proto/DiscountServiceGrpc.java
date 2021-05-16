package com.hash.challenge.api.rpc.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.34.0)",
    comments = "Source: discount.proto")
public final class DiscountServiceGrpc {

  private DiscountServiceGrpc() {}

  public static final String SERVICE_NAME = "discount.DiscountService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<DiscountInputProto.DiscountRequest,
      DiscountInputProto.DiscountResponse> getDiscountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Discount",
      requestType = DiscountInputProto.DiscountRequest.class,
      responseType = DiscountInputProto.DiscountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<DiscountInputProto.DiscountRequest,
      DiscountInputProto.DiscountResponse> getDiscountMethod() {
    io.grpc.MethodDescriptor<DiscountInputProto.DiscountRequest, DiscountInputProto.DiscountResponse> getDiscountMethod;
    if ((getDiscountMethod = DiscountServiceGrpc.getDiscountMethod) == null) {
      synchronized (DiscountServiceGrpc.class) {
        if ((getDiscountMethod = DiscountServiceGrpc.getDiscountMethod) == null) {
          DiscountServiceGrpc.getDiscountMethod = getDiscountMethod =
              io.grpc.MethodDescriptor.<DiscountInputProto.DiscountRequest, DiscountInputProto.DiscountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Discount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  DiscountInputProto.DiscountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  DiscountInputProto.DiscountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DiscountServiceMethodDescriptorSupplier("Discount"))
              .build();
        }
      }
    }
    return getDiscountMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DiscountServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DiscountServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DiscountServiceStub>() {
        @java.lang.Override
        public DiscountServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DiscountServiceStub(channel, callOptions);
        }
      };
    return DiscountServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DiscountServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DiscountServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DiscountServiceBlockingStub>() {
        @java.lang.Override
        public DiscountServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DiscountServiceBlockingStub(channel, callOptions);
        }
      };
    return DiscountServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DiscountServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DiscountServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DiscountServiceFutureStub>() {
        @java.lang.Override
        public DiscountServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DiscountServiceFutureStub(channel, callOptions);
        }
      };
    return DiscountServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class DiscountServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void discount(DiscountInputProto.DiscountRequest request,
        io.grpc.stub.StreamObserver<DiscountInputProto.DiscountResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDiscountMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDiscountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                DiscountInputProto.DiscountRequest,
                DiscountInputProto.DiscountResponse>(
                  this, METHODID_DISCOUNT)))
          .build();
    }
  }

  /**
   */
  public static final class DiscountServiceStub extends io.grpc.stub.AbstractAsyncStub<DiscountServiceStub> {
    private DiscountServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DiscountServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DiscountServiceStub(channel, callOptions);
    }

    /**
     */
    public void discount(DiscountInputProto.DiscountRequest request,
        io.grpc.stub.StreamObserver<DiscountInputProto.DiscountResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDiscountMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class DiscountServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<DiscountServiceBlockingStub> {
    private DiscountServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DiscountServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DiscountServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public DiscountInputProto.DiscountResponse discount(DiscountInputProto.DiscountRequest request) {
      return blockingUnaryCall(
          getChannel(), getDiscountMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class DiscountServiceFutureStub extends io.grpc.stub.AbstractFutureStub<DiscountServiceFutureStub> {
    private DiscountServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DiscountServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DiscountServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<DiscountInputProto.DiscountResponse> discount(
        DiscountInputProto.DiscountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDiscountMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DISCOUNT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DiscountServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DiscountServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_DISCOUNT:
          serviceImpl.discount((DiscountInputProto.DiscountRequest) request,
              (io.grpc.stub.StreamObserver<DiscountInputProto.DiscountResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class DiscountServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DiscountServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return DiscountInputProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DiscountService");
    }
  }

  private static final class DiscountServiceFileDescriptorSupplier
      extends DiscountServiceBaseDescriptorSupplier {
    DiscountServiceFileDescriptorSupplier() {}
  }

  private static final class DiscountServiceMethodDescriptorSupplier
      extends DiscountServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DiscountServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (DiscountServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DiscountServiceFileDescriptorSupplier())
              .addMethod(getDiscountMethod())
              .build();
        }
      }
    }
    return result;
  }
}
