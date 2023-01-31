package com.xiaozhu.spi;

public class ClientAService implements ClientService{
    @Override
    public void execute() {
        System.out.println("I'm A Service");
    }
}
