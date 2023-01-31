package com.xiaozhu.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioMain {
    public static void main(String[] args) {

        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(5555);
            serverSocketChannel.socket().bind(inetSocketAddress);
            serverSocketChannel.configureBlocking(false);

            // selector, register accept
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                selector.select(2000);
                // 遍历selectionKey
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if(key.isAcceptable()) {  //处理连接事件
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);  //设置为非阻塞
                        System.out.println("client:" + socketChannel.getLocalAddress() + " is connect");
                        socketChannel.register(selector, SelectionKey.OP_READ); //注册客户端读取事件到selector
                    } else if (key.isReadable()) {  //处理读取事件
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        SocketChannel channel = (SocketChannel) key.channel();
                        channel.read(byteBuffer);
                        System.out.println("client:" + channel.getLocalAddress() + " send " + new String(byteBuffer.array()));
                    }
                    iterator.remove();  //事件处理完毕，要记得清除
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
