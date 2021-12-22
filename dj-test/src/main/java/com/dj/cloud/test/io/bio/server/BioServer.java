package com.dj.cloud.test.io.bio.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class BioServer extends Thread {

    private ServerSocket serverSocket = null;

    public static void main(String[] args) {
        new BioServer().start();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(8080);
//            serverSocket.bind(new InetSocketAddress(8080));
            System.out.println("服务器启动成功！");
            while (true) {
                Socket socket = serverSocket.accept();
                BioServerHandler bioServerHandler = new BioServerHandler(socket, Charset.forName("utf-8"));
                bioServerHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
