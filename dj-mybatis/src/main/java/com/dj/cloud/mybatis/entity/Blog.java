package com.dj.cloud.mybatis.entity;

public class Blog {
    private Integer id;
    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }
}
