package com.dj.cloud.test.nio.socket.sssocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;

public class Test1_Server1 {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress("localhost", 8888));
        System.out.println("isBlocking: " + serverSocketChannel.isBlocking());
        Socket socket = serverSocket.accept();
        InputStream is = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(is);

        char[] chars = new char[1024];
        int readLength = inputStreamReader.read(chars);

        while (readLength != -1) {
            String newString = new String(chars, 0, readLength);
            System.out.println(newString);
            readLength = inputStreamReader.read(chars);
        }

        inputStreamReader.close();
        is.close();
        socket.close();
        serverSocket.close();
        serverSocketChannel.close();
    }
}
