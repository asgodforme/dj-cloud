package com.dj.cloud.test.nio.socket.a1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) throws IOException {
        byte[] byteArray = new byte[1024];
        ServerSocket serverSocket = new ServerSocket(8088);
        System.out.println("accept begin " + System.currentTimeMillis());
        Socket accept = serverSocket.accept();
        System.out.println("accept end " + System.currentTimeMillis());

//        InputStream is = accept.getInputStream();
//        System.out.println("read begin " + System.currentTimeMillis());
//        int length = is.read(byteArray);
//        System.out.println("read end " + System.currentTimeMillis());
//        while (length != -1) {
//            String newString = new String(byteArray, 0, length);
//            System.out.println("receive: " + newString);
//            length = is.read(byteArray);
//        }

        OutputStream outputStream = accept.getOutputStream();
        outputStream.write("我是服务端！".getBytes(StandardCharsets.UTF_8));
        outputStream.close();

//        is.close();
        accept.close();
        serverSocket.close();
    }
}
