package com.dj.cloud.test.nio.socket.a2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) throws IOException {

        Socket accept = new Socket("localhost", 8088);

        OutputStream outputStream = accept.getOutputStream();
        String a = "hello server a";
        String b = "hello server b";
        String c = "hello server c";

        int allStrByteLength = (a + b + c).getBytes(StandardCharsets.UTF_8).length;

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeInt(allStrByteLength);
        objectOutputStream.flush();

        objectOutputStream.write(a.getBytes(StandardCharsets.UTF_8));
        objectOutputStream.write(b.getBytes(StandardCharsets.UTF_8));
        objectOutputStream.write(c.getBytes(StandardCharsets.UTF_8));
        objectOutputStream.flush();

        InputStream inputStream = accept.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        int byteLength = objectInputStream.readInt();
        byte[] byteArray = new byte[byteLength];
        objectInputStream.readFully(byteArray);

        String newString = new String(byteArray);
        System.out.println(newString);

        objectOutputStream.close();
        outputStream.close();
        objectInputStream.close();
        accept.close();
    }
}
