package com.dj.cloud.common.vo;

public class Result<T> {

    private String status;
    private String type;
    private String currentAuthority;
    private String responseCode;
    private String responseMessage;
    private T payload;

    public static <T> Result<T> newResult(T payload) {
        return new Result<T>("success", "SC0000", "", payload);
    }

    public static <T> Result<T> newResult(T payload, String type) {
        return new Result<T>("success", "SC0000", "", payload, type);
    }

    public static <T> Result<T> newFailResult(String responseCode, String responseMessage, T payload) {
        return new Result<T>("fail", responseCode, responseMessage, payload);
    }

    public Result(String status, String responseCode, String responseMessage, T payload) {
        this.status = status;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.payload = payload;
    }

    public Result(String status, String responseCode, String responseMessage, T payload, String type) {
        this.status = status;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.payload = payload;
        this.type = type;
    }

    public Result(String status, String type, String currentAuthority) {
        this.status = status;
        this.type = type;
        this.currentAuthority = currentAuthority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrentAuthority() {
        return currentAuthority;
    }

    public void setCurrentAuthority(String currentAuthority) {
        this.currentAuthority = currentAuthority;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
