package com.dj.cloud.common.vo;

public class PageResponse<T> {

    private T data;

    private long total;

    public PageResponse() {
    }

    public PageResponse(T data, long total) {
        this.data = data;
        this.total = total;
    }

    public static <T> PageResponse<T> of(long total, T data) {
        return new PageResponse(data, total);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
