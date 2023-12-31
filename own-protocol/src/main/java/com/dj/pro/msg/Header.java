package com.dj.pro.msg;

import java.util.HashMap;
import java.util.Map;

public class Header {

    private int crcCode = 0xabef0101;
    private int length; // 消息长度
    private long sessionId; // 会话id
    private byte type; // 消息类型
    private byte priority; // 消息优先级
    private Map<String, Object> attachement = new HashMap<>(); // 附件

    public int getCrcCode() {
        return crcCode;
    }

    public void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public Map<String, Object> getAttachement() {
        return attachement;
    }

    public void setAttachement(Map<String, Object> attachement) {
        this.attachement = attachement;
    }

    @Override
    public String toString() {
        return "Header{" +
                "crcCode=" + crcCode +
                ", length=" + length +
                ", sessionId=" + sessionId +
                ", type=" + type +
                ", priority=" + priority +
                ", attachement=" + attachement +
                '}';
    }
}
