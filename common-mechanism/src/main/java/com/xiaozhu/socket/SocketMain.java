package com.xiaozhu.socket;

import java.io.IOException;
import java.net.Socket;

public class SocketMain {
    public static void main(String[] args) throws IOException {
        // Socket 客户端
        String endpointHost = "39.156.66.10";
        int port = 801;
        Socket socket = new Socket(endpointHost, port);
        // 连接 Endpoint，只需看Socket是否创建成功

        if(socket == null) {
            System.out.println("Endpoint Connection Failed.");
        }

        socket.close();
    }
}
