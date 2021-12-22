package com.dj.cloud.test.nio.socket.a2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8088);
        Socket accept = serverSocket.accept();

        InputStream inputStream = accept.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        int byteLength = objectInputStream.readInt();
        byte[] byteArray = new byte[byteLength];
        objectInputStream.readFully(byteArray);

        String newString = new String(byteArray);
        System.out.println(newString);

        OutputStream outputStream = accept.getOutputStream();
        String a = "hello client a";
        String b = "hello client b";
        String c = "hello client c";

        int allStrByteLength = (a + b + c).getBytes(StandardCharsets.UTF_8).length;

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeInt(allStrByteLength);
        objectOutputStream.flush();

        objectOutputStream.write(a.getBytes(StandardCharsets.UTF_8));
        objectOutputStream.write(b.getBytes(StandardCharsets.UTF_8));
        objectOutputStream.write(c.getBytes(StandardCharsets.UTF_8));
        objectOutputStream.flush();


        objectOutputStream.close();
        outputStream.close();
        objectInputStream.close();
        accept.close();
        serverSocket.close();
    }
}
