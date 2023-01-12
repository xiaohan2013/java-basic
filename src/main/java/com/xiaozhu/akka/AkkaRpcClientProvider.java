package com.xiaozhu.akka;

import java.lang.reflect.Proxy;

// 创建AkkaRpcClient，并提供用户Rpc服务的动态代理。
public class AkkaRpcClientProvider<T> {
    private String address;
    private Class<T> interfaceClass;


    public AkkaRpcClientProvider<T> setInterfaceClass(Class<T> interfaceClass) {
        this.interfaceClass = interfaceClass;
        return this;
    }

    public AkkaRpcClientProvider<T> setAddress(String address) {
        this.address = address;
        return this;
    }


    @SuppressWarnings("unchecked")
    public T get() {
        AkkaRpcClient client = new AkkaRpcClient();
        try {
            client.connect(this.address);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AkkaRpcInvocationHandler handler = new AkkaRpcInvocationHandler(client);
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, handler);

    }
}
