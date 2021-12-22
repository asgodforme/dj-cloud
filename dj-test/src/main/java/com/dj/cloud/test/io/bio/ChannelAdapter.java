package com.dj.cloud.test.io.bio;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.Charset;

public abstract class ChannelAdapter extends Thread {

    private Socket socket;
    private ChannelHandler channelHandler;
    private Charset charset;

    public ChannelAdapter(Socket socket, Charset charset) {
        this.socket = socket;
        this.charset = charset;
        while (!socket.isConnected()) {
            break;
        }
        channelHandler = new ChannelHandler(this.socket, this.charset);
        channelActive(channelHandler);
    }

    @Override
    public void run() {
        try {
            System.out.println("read....");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), this.charset));
            System.out.println("after read...");
            String str = null;
            while ((str = bufferedReader.readLine())!= null) {
                channelRead(channelHandler, str);
            }
            System.out.println("after after...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void channelActive(ChannelHandler ctx);

    public abstract void channelRead(ChannelHandler ctx, String msg);
}
