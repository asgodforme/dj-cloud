package com.dj.cloud.mybatis.entity;

import java.io.Serializable;
import java.util.List;

public class Blog implements Serializable {
    private Integer id;
    private String comment;
    private String title;
    private List<Comments> comments;

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public Blog() {
    }

    public Blog(Integer id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

//    @Override
//    public String toString() {
//        return "Blog{" +
//                "id=" + id +
//                ", comment='" + comment + '\'' +
//                ", title='" + title + '\'' +
//                ", comments=" + comments +
//                '}';
//    }
}
