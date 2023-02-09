package com.dj.cloud.mybatis.entity;

import java.io.Serializable;

public class Comments implements Serializable {
    private Integer id;
    private Integer blogId;
    private String comment;
    private Blog blog;

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

//    @Override
//    public String toString() {
//        return "Comments{" +
//                "id=" + id +
//                ", blogId=" + blogId +
//                ", comment='" + comment + '\'' +
//                ", blog=" + blog +
//                '}';
//    }
}
