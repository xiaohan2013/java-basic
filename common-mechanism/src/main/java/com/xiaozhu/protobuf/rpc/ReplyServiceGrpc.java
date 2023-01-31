package com.xiaozhu.protobuf.rpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.51.1)",
    comments = "Source: message.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ReplyServiceGrpc {

  private ReplyServiceGrpc() {}

  public static final String SERVICE_NAME = "ReplyService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.xiaozhu.protobuf.rpc.Message.Msg,
      com.xiaozhu.protobuf.rpc.Message.Msg> getCallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "call",
      requestType = com.xiaozhu.protobuf.rpc.Message.Msg.class,
      responseType = com.xiaozhu.protobuf.rpc.Message.Msg.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.xiaozhu.protobuf.rpc.Message.Msg,
      com.xiaozhu.protobuf.rpc.Message.Msg> getCallMethod() {
    io.grpc.MethodDescriptor<com.xiaozhu.protobuf.rpc.Message.Msg, com.xiaozhu.protobuf.rpc.Message.Msg> getCallMethod;
    if ((getCallMethod = ReplyServiceGrpc.getCallMethod) == null) {
      synchronized (ReplyServiceGrpc.class) {
        if ((getCallMethod = ReplyServiceGrpc.getCallMethod) == null) {
          ReplyServiceGrpc.getCallMethod = getCallMethod =
              io.grpc.MethodDescriptor.<com.xiaozhu.protobuf.rpc.Message.Msg, com.xiaozhu.protobuf.rpc.Message.Msg>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "call"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xiaozhu.protobuf.rpc.Message.Msg.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xiaozhu.protobuf.rpc.Message.Msg.getDefaultInstance()))
              .setSchemaDescriptor(new ReplyServiceMethodDescriptorSupplier("call"))
              .build();
        }
      }
    }
    return getCallMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ReplyServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReplyServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReplyServiceStub>() {
        @java.lang.Override
        public ReplyServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReplyServiceStub(channel, callOptions);
        }
      };
    return ReplyServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ReplyServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReplyServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReplyServiceBlockingStub>() {
        @java.lang.Override
        public ReplyServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReplyServiceBlockingStub(channel, callOptions);
        }
      };
    return ReplyServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ReplyServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReplyServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReplyServiceFutureStub>() {
        @java.lang.Override
        public ReplyServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReplyServiceFutureStub(channel, callOptions);
        }
      };
    return ReplyServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ReplyServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void call(com.xiaozhu.protobuf.rpc.Message.Msg request,
        io.grpc.stub.StreamObserver<com.xiaozhu.protobuf.rpc.Message.Msg> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCallMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCallMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.xiaozhu.protobuf.rpc.Message.Msg,
                com.xiaozhu.protobuf.rpc.Message.Msg>(
                  this, METHODID_CALL)))
          .build();
    }
  }

  /**
   */
  public static final class ReplyServiceStub extends io.grpc.stub.AbstractAsyncStub<ReplyServiceStub> {
    private ReplyServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReplyServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReplyServiceStub(channel, callOptions);
    }

    /**
     */
    public void call(com.xiaozhu.protobuf.rpc.Message.Msg request,
        io.grpc.stub.StreamObserver<com.xiaozhu.protobuf.rpc.Message.Msg> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCallMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ReplyServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<ReplyServiceBlockingStub> {
    private ReplyServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReplyServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReplyServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.xiaozhu.protobuf.rpc.Message.Msg call(com.xiaozhu.protobuf.rpc.Message.Msg request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCallMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ReplyServiceFutureStub extends io.grpc.stub.AbstractFutureStub<ReplyServiceFutureStub> {
    private ReplyServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReplyServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReplyServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.xiaozhu.protobuf.rpc.Message.Msg> call(
        com.xiaozhu.protobuf.rpc.Message.Msg request) {
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
    private final ReplyServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ReplyServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CALL:
          serviceImpl.call((com.xiaozhu.protobuf.rpc.Message.Msg) request,
              (io.grpc.stub.StreamObserver<com.xiaozhu.protobuf.rpc.Message.Msg>) responseObserver);
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

  private static abstract class ReplyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ReplyServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.xiaozhu.protobuf.rpc.Message.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ReplyService");
    }
  }

  private static final class ReplyServiceFileDescriptorSupplier
      extends ReplyServiceBaseDescriptorSupplier {
    ReplyServiceFileDescriptorSupplier() {}
  }

  private static final class ReplyServiceMethodDescriptorSupplier
      extends ReplyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ReplyServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ReplyServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ReplyServiceFileDescriptorSupplier())
              .addMethod(getCallMethod())
              .build();
        }
      }
    }
    return result;
  }
}
