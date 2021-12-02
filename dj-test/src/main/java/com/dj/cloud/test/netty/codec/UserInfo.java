package com.dj.cloud.test.netty.codec;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class UserInfo implements Serializable {

    private String userName;
    private int userId;

    public UserInfo buildUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserInfo buildUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public byte[] codeC() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] value = this.userName.getBytes(StandardCharsets.UTF_8);
        buffer.putInt(value.length);
        buffer.put(value);
        buffer.putInt(this.userId);
        buffer.flip();
        value = null;
        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return result;
    }

    public UserInfo decode(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        int i = buffer.getInt();
        System.out.println("i : " + i);
        byte[] nameByte = new byte[i];
        buffer.get(nameByte, 0, i);
        String name = new String(nameByte);
        int userId = buffer.getInt();
        System.out.println("name: " + name);
        System.out.println("userId:" + userId);
        buildUserId(userId);
        buildUserName(name);
        return this;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
