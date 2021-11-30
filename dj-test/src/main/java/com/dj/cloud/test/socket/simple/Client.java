package com.dj.cloud.test.socket.simple;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.setSoTimeout(3000);
        socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 2000), 3000);

        System.out.println("已发起服务器连接，并进入后续流程....");
        System.out.println("客户端信息：" + socket.getLocalAddress() + " P: " + socket.getLocalPort());
        System.out.println("服务器信息：" + socket.getInetAddress() + " P: " + socket.getPort());

        try {
            todo(socket);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("异常关闭");
        }

        socket.close();
        System.out.println("客户端已退出");
    }

    private static void todo(Socket socket) throws IOException {
        InputStream in = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(in));

        // Socket输出流
        OutputStream outputStream = socket.getOutputStream();
        PrintStream socketPrintStream = new PrintStream(outputStream);

        // Socket输入流
        InputStream inputStream = socket.getInputStream();
        BufferedReader socketBufferdReader = new BufferedReader(new InputStreamReader(inputStream));

        boolean flag = true;
        do {
            String str = input.readLine();
            socketPrintStream.println(str);

            String echo = socketBufferdReader.readLine();
            if ("bye".equalsIgnoreCase(echo)) {
                flag = false;
            } else {
                System.out.println("服务器发送过来的数据： " + echo);
            }
        } while (flag);

        socketBufferdReader.close();
        socketPrintStream.close();
        input.close();
    }
}
