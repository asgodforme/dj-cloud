package com.dj.cloud.test.io.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.charset.Charset;

public class ChannelHandler {

    private Socket socket;
    private Charset charset;

    public ChannelHandler(Socket socket, Charset charset) {
        this.socket = socket;
        this.charset = charset;
    }

    public Socket socket() {
        return this.socket;
    }

    public Charset charset() {
        return this.charset;
    }

    public void writeAndFlush(String msg) {
        PrintStream os = null;
        try {
            os = new PrintStream(socket.getOutputStream());
            os.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
