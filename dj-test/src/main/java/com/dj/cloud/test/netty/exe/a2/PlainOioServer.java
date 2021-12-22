package com.dj.cloud.test.netty.exe.a2;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class PlainOioServer {
    public void serve(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);
        while (true) {
            final Socket client = socket.accept();
            System.out.println("接收到请求：" + client);
            new Thread(() -> {
                try {
                    OutputStream os = client.getOutputStream();
                    os.write("Hi~".getBytes(StandardCharsets.UTF_8));
                    os.flush();
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
