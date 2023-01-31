package com.xiaozhu.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {
    public static void main(String[] args) {

        try {
            SocketChannel socketChannel = SocketChannel.open();

            socketChannel.configureBlocking(false);

            InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 5555);

            if(!socketChannel.connect(inetSocketAddress)) {
                while (!socketChannel.finishConnect()) {
                    System.out.println("Connecting....");
                }
            }

            ByteBuffer byteBuffer = ByteBuffer.wrap("MikeChen".getBytes());
            socketChannel.write(byteBuffer);
            socketChannel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
