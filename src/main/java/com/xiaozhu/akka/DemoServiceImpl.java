package com.xiaozhu.akka;

public class DemoServiceImpl implements DemoService{
    @Override
    public String sayHello(String name) {
        return "This is akka RPC service.\nHello " + name;
    }

    @Override
    public String sayGoodbye(String name) {
        return "This is akka RPC service.\nGoodbye " + name;
    }
}
