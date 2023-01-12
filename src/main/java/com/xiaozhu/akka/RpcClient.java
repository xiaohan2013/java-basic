package com.xiaozhu.akka;

public class RpcClient {
    public static void main(String[] args) {
        AkkaRpcClientProvider<DemoService> clientProvider = new AkkaRpcClientProvider<>();
        clientProvider.setAddress("akka.tcp://rpcSys@0.0.0.0:10086/user/akkaRpcServer");
        clientProvider.setInterfaceClass(DemoService.class);

        DemoService demoService = clientProvider.get();
        String result = demoService.sayHello("akka");
        System.out.println(result);
    }
}
