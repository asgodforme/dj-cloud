package com.dj.cloud.object.vo;

public class Result {

    private String status;
    private String type;
    private String currentAuthority;

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
}
