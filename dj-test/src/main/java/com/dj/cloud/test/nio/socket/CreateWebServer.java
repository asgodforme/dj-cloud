package com.dj.cloud.test.nio.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CreateWebServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String getStr = "";
        while (!"".equals(getStr = br.readLine())) {
            System.out.println(getStr);
        }

        OutputStream os = socket.getOutputStream();
        os.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
        os.write("<html><body><h1>HELLO WORLD!</h1></body></html>".getBytes());

        os.flush();
        is.close();
        os.close();
        socket.close();
        serverSocket.close();
    }
}
