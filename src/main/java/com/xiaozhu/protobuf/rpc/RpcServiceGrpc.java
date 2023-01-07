package com.xiaozhu.protobuf.rpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;


/**
 * <pre>
 *interface
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.51.1)",
    comments = "Source: message.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class RpcServiceGrpc {

  private RpcServiceGrpc() {}

  public static final String SERVICE_NAME = "RpcService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.xiaozhu.protobuf.rpc.Message.Request,
      com.xiaozhu.protobuf.rpc.Message.Response> getCallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "call",
      requestType = com.xiaozhu.protobuf.rpc.Message.Request.class,
      responseType = com.xiaozhu.protobuf.rpc.Message.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xiaozhu.protobuf.rpc.Message.Request,
      com.xiaozhu.protobuf.rpc.Message.Response> getCallMethod() {
    io.grpc.MethodDescriptor<com.xiaozhu.protobuf.rpc.Message.Request, com.xiaozhu.protobuf.rpc.Message.Response> getCallMethod;
    if ((getCallMethod = RpcServiceGrpc.getCallMethod) == null) {
      synchronized (RpcServiceGrpc.class) {
        if ((getCallMethod = RpcServiceGrpc.getCallMethod) == null) {
          RpcServiceGrpc.getCallMethod = getCallMethod =
              io.grpc.MethodDescriptor.<com.xiaozhu.protobuf.rpc.Message.Request, com.xiaozhu.protobuf.rpc.Message.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "call"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xiaozhu.protobuf.rpc.Message.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xiaozhu.protobuf.rpc.Message.Response.getDefaultInstance()))
              .setSchemaDescriptor(new RpcServiceMethodDescriptorSupplier("call"))
              .build();
        }
      }
    }
    return getCallMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RpcServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RpcServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RpcServiceStub>() {
        @java.lang.Override
        public RpcServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RpcServiceStub(channel, callOptions);
        }
      };
    return RpcServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RpcServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RpcServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RpcServiceBlockingStub>() {
        @java.lang.Override
        public RpcServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RpcServiceBlockingStub(channel, callOptions);
        }
      };
    return RpcServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RpcServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RpcServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RpcServiceFutureStub>() {
        @java.lang.Override
        public RpcServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RpcServiceFutureStub(channel, callOptions);
        }
      };
    return RpcServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   *interface
   * </pre>
   */
  public static abstract class RpcServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void call(com.xiaozhu.protobuf.rpc.Message.Request request,
        io.grpc.stub.StreamObserver<com.xiaozhu.protobuf.rpc.Message.Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCallMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCallMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.xiaozhu.protobuf.rpc.Message.Request,
                com.xiaozhu.protobuf.rpc.Message.Response>(
                  this, METHODID_CALL)))
          .build();
    }
  }

  /**
   * <pre>
   *interface
   * </pre>
   */
  public static final class RpcServiceStub extends io.grpc.stub.AbstractAsyncStub<RpcServiceStub> {
    private RpcServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RpcServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RpcServiceStub(channel, callOptions);
    }

    /**
     */
    public void call(com.xiaozhu.protobuf.rpc.Message.Request request,
        io.grpc.stub.StreamObserver<com.xiaozhu.protobuf.rpc.Message.Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCallMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *interface
   * </pre>
   */
  public static final class RpcServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<RpcServiceBlockingStub> {
    private RpcServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RpcServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RpcServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.xiaozhu.protobuf.rpc.Message.Response call(com.xiaozhu.protobuf.rpc.Message.Request request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCallMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *interface
   * </pre>
   */
  public static final class RpcServiceFutureStub extends io.grpc.stub.AbstractFutureStub<RpcServiceFutureStub> {
    private RpcServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RpcServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RpcServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xiaozhu.protobuf.rpc.Message.Response> call(
        com.xiaozhu.protobuf.rpc.Message.Request request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCallMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CALL = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RpcServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RpcServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CALL:
          serviceImpl.call((com.xiaozhu.protobuf.rpc.Message.Request) request,
              (io.grpc.stub.StreamObserver<com.xiaozhu.protobuf.rpc.Message.Response>) responseObserver);
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

  private static abstract class RpcServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RpcServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.xiaozhu.protobuf.rpc.Message.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RpcService");
    }
  }

  private static final class RpcServiceFileDescriptorSupplier
      extends RpcServiceBaseDescriptorSupplier {
    RpcServiceFileDescriptorSupplier() {}
  }

  private static final class RpcServiceMethodDescriptorSupplier
      extends RpcServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RpcServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (RpcServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RpcServiceFileDescriptorSupplier())
              .addMethod(getCallMethod())
              .build();
        }
      }
    }
    return result;
  }
}
