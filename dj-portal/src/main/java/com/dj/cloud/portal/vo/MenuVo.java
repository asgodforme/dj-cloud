package com.dj.cloud.portal.vo;

import java.util.List;

/**
 * 返回给前端的菜单对象
 */
public class MenuVo {

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
     * 页面组件名： 约定于访问路径一致
     */
    private String component;

    private List<MenuVo> routes;

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

    public List<MenuVo> getRoutes() {
        return routes;
    }

    public void setRoutes(List<MenuVo> routes) {
        this.routes = routes;
    }
}
