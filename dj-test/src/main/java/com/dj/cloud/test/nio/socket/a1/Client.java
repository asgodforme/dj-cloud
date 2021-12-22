package com.dj.cloud.test.nio.socket.a1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("socket begin " + System.currentTimeMillis());
        Socket localhost = new Socket("localhost", 8088);
        System.out.println("socket end " + System.currentTimeMillis());

        OutputStream outputStream = localhost.getOutputStream();
        outputStream.write("我是讲解".getBytes(StandardCharsets.UTF_8));


        byte[] byteArray = new byte[1024];
        InputStream is = localhost.getInputStream();
        System.out.println("read begin " + System.currentTimeMillis());
        int length = is.read(byteArray);
        System.out.println("read end " + System.currentTimeMillis());
        while (length != -1) {
            String newString = new String(byteArray, 0, length);
            System.out.println("receive: " + newString);
            length = is.read(byteArray);
        }

        outputStream.close();
        localhost.close();
    }
}
