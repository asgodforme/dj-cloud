package com.dj.cloud.test.io.aio.plus.client;

public class TimeClient {
    public static void main(String[] args) {
        new Thread(new AsyncTimeClientHandler("127.0.0.1", 8080)).start();
    }
}
