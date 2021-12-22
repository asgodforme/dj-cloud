package com.dj.cloud.test.nio.socket;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("阻塞开始： " + System.currentTimeMillis());
        serverSocket.accept();
        System.out.println("阻塞结束： " + System.currentTimeMillis());
        serverSocket.close();
    }
}
