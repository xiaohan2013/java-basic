package com.xiaozhu.spi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public class ServiceLoaderMain {
    public static void main(String[] args) {
        final ServiceLoader<ClientService> loader =
                ServiceLoader.load(ClientService.class);

        final Iterator<ClientService> factories = loader.iterator();
        while (factories.hasNext()) {
            ClientService service = factories.next();
            service.execute();
        }
    }
}
