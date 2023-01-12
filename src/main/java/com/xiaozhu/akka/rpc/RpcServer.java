package com.xiaozhu.akka.rpc;

import akka.actor.ActorRef;

public class RpcServer {
    public static void main(String[] args) {
        DemoServiceImpl demoService = new DemoServiceImpl();
        AkkaRpcServerProvider serverProvider = new AkkaRpcServerProvider();
        serverProvider.setPort(10086);
        serverProvider.setName("akkaRpcServer");
        serverProvider.setRef(demoService);
        serverProvider.setInterfaceClass(DemoService.class);
        ActorRef actorRef = serverProvider.get();
        System.out.println(actorRef.path());
    }
}
