package com.dj.cloud.common.exception;

public class CoreException extends Exception {

    private String responseCode;
    private String responseMessage;

    public CoreException(String responseCode, String responseMessage) {
        super(responseMessage);
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
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
}
