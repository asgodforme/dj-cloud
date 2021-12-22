package com.dj.cloud.test.io.aio.plus.server;

public class TimeServer {
    public static void main(String[] args) {
        AsyncTimeServerHandler asyncTimeServerHandler = new AsyncTimeServerHandler(8080);
        asyncTimeServerHandler.start();
    }
}
