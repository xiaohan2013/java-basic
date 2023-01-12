package com.xiaozhu.akka;

import akka.actor.UntypedAbstractActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class AkkaRpcServerActor<T> extends UntypedAbstractActor {
    private static final Logger LOG = LoggerFactory.getLogger(AkkaRpcServerActor.class);
    private final T ref;
    private final Class<?> interfaceClass;


    public AkkaRpcServerActor(T ref, Class<?> interfaceClass) {
        this.ref = ref;
        this.interfaceClass = interfaceClass;
    }

    @Override
    public void onReceive(Object message) throws Throwable, Throwable {
        if(message instanceof RpcRequest) {
            RpcRequest request = (RpcRequest) message;
            LOG.info("Received request:{}", request);
            // 处理请求
            RpcResponse response = handleRequest(request);
            // 将结果返回给客户端
            LOG.info("Send response to client.{}", response);
            getSender().tell(response, getSelf());
        }
    }

    private RpcResponse handleRequest(RpcRequest request) {
        RpcResponse response = new RpcResponse();
        try {
            LOG.info("The server is handling request.");
            Method method = interfaceClass.getMethod(request.getMethodName(), request.getParameterTypes());
            Object data = method.invoke(ref, request.getParameters());
            response.setData(data);
        } catch (Exception e) {
            response.setStatus(RpcResponse.FAILED).setMessage(e.getMessage());
        }
        return response;
    }

}
