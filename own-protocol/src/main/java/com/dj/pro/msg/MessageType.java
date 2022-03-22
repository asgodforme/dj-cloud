package com.dj.pro.msg;

public enum MessageType {

    LOGIN_REQ(3),
    LOGIN_RESP(4),
    HEARTBEAT_REQ(5),
    HEARTBEAT_RESP(6)
    ;

    private final int msgType;

    MessageType(int msgType) {
        this.msgType = msgType;
    }

    public byte value() {
        return (byte) msgType;
    }
}
