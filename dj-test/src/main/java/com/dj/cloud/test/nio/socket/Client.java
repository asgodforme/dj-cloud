package com.dj.cloud.test.nio.socket;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("连接准备：" + System.currentTimeMillis());
        Socket localhost = new Socket("localhost", 8080);
        System.out.println("连接结束：" + System.currentTimeMillis());
        localhost.close();

    }
}
