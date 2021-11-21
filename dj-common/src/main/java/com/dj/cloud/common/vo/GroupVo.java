package com.dj.cloud.common.vo;

import com.dj.cloud.common.base.CommonObject;

import java.util.Arrays;

public class GroupVo extends CommonObject {

    private Integer id;

    private String groupName;

    private String isUse;

    private Integer[] userIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public Integer[] getUserIds() {
        return userIds;
    }

    public void setUserIds(Integer[] userIds) {
        this.userIds = userIds;
    }
}
