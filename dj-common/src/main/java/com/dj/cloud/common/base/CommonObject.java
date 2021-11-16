package com.dj.cloud.common.base;

import javax.persistence.Transient;

public class CommonObject {

    @Transient
    private Integer current;
    @Transient
    private Integer pageSize;

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
