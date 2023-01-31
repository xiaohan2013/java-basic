package com.xiaozhu.thrift;

import com.xiaozhu.thrift.gen.Calculator;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

public class ThriftServer {
    private static CalculatorHandler handler;
    private static Calculator.Processor processor;

    public static void main(String[] args) {

        handler = new CalculatorHandler();

        processor = new Calculator.Processor(handler);

        Runnable simple = new Runnable() {
            @Override
            public void run() {
                simple(processor);
            }
        };
    }

    public static void simple(Calculator.Processor processor) {

        try {
            TServerTransport serverTransport = new TServerSocket(9090);

            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

            // Use this for a multithreaded server
            // TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

            System.out.println("Starting the simple server...");
            server.serve();

        } catch (TTransportException e) {
            throw new RuntimeException(e);
        }

    }

    public static void secure(Calculator.Processor processor) {
        /*
         * Use TSSLTransportParameters to setup the required SSL parameters. In this example
         * we are setting the keystore and the keystore password. Other things like algorithms,
         * cipher suites, client auth etc can be set.
         */
        try {
            TSSLTransportFactory.TSSLTransportParameters parameters = new TSSLTransportFactory.TSSLTransportParameters();

            // Keystore containes the private key
            parameters.setKeyStore("", "thrift", null, null);

            /*
             * Use any of the TSSLTransportFactory to get a server transport with the appropriate
             * SSL configuration. You can use the default settings if properties are set in the command line.
             * Ex: -Djavax.net.ssl.keyStore=.keystore and -Djavax.net.ssl.keyStorePassword=thrift
             *
             * Note: You need not explicitly call open(). The underlying server socket is bound on return
             * from the factory class.
             */
            TServerTransport serverTransport = null;

            serverTransport = TSSLTransportFactory.getServerSocket(9091, 0, null, parameters);

            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

            // Use this for a multi threaded server
            // TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

            System.out.println("Starting the secure server...");
            server.serve();
        } catch (TTransportException e) {
            throw new RuntimeException(e);
        }

    }
}
