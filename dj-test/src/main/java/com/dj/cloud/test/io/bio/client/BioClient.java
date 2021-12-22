package com.dj.cloud.test.io.bio.client;

import com.dj.cloud.test.io.bio.ChannelHandler;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

public class BioClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8080);
            System.out.println("初始化客户端成功");
            BioClientHandler bioClientHandler = new BioClientHandler(socket, Charset.forName("utf-8"));
            bioClientHandler.start();
            Thread.sleep(1000);
            new ChannelHandler(socket, Charset.forName("utf-8")).writeAndFlush("hello, I am client");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
