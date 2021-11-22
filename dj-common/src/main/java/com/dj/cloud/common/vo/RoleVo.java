package com.dj.cloud.common.vo;

import com.dj.cloud.common.base.CommonObject;

public class RoleVo extends CommonObject {

    private Integer id;

    private String roleName;

    private String isUse;

    private Integer[] permissionIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public Integer[] getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(Integer[] permissionIds) {
        this.permissionIds = permissionIds;
    }

}
