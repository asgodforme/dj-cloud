package com.dj.cloud.portal.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Menu {
    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 访问路径
     */
    private String path;
    /**
     * 名称
     */
    private String name;
    /**
     * 图标
     */
    private String icon;
    /**
     * 页面组件名： 约定与访问路径一致
     */
    private String component;
    /**
     * 父节点id
     */
    private Integer parentId;
    /**
     * 显示顺序
     */
    private Integer displaySeq;

    /**
     * 是否显示
     */
    private boolean display;

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getDisplaySeq() {
        return displaySeq;
    }

    public void setDisplaySeq(Integer displaySeq) {
        this.displaySeq = displaySeq;
    }
}
