package com.dj.cloud.test.netty.codec;

import java.io.*;

/**
 * 一个编解码框架的优劣：
 * 1. 是否支持跨语言，支持的语言种类是否丰富
 * 2.编码后的码流大小
 * 3. 编解码的性能
 * 4. 类库是否小巧，API使用是否方便
 * 5. 使用者需要手工开发的工作量和难度。
 */
public class TestUserInfo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        UserInfo userInfo = new UserInfo();
        userInfo.buildUserId(100).buildUserName("Welcome to Netty");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(userInfo);
        os.flush();
        os.close();
        byte[] b = bos.toByteArray();
        System.out.println("JDK:" + b.length);
        bos.close();
        System.out.println("------------------------");
        System.out.println("the byte array serializable length is : " + userInfo.codeC().length);
        System.out.println(userInfo.decode(userInfo.codeC()));

        ByteArrayInputStream bis = new ByteArrayInputStream(b);
        ObjectInputStream is = new ObjectInputStream(bis);
        UserInfo res = (UserInfo) is.readObject();
        System.out.println(res);
    }
}
