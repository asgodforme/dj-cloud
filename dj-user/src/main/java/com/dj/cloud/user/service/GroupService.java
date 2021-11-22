package com.dj.cloud.user.service;

import com.dj.cloud.common.exception.CoreException;
import com.dj.cloud.common.vo.PageResponse;
import com.dj.cloud.common.vo.Result;
import com.dj.cloud.common.vo.GroupVo;
import com.dj.cloud.user.entity.Group;
import com.dj.cloud.user.entity.Role;

import java.util.List;

public interface GroupService {

    Result<Group> addGroup(GroupVo groupVo) throws CoreException;

    Result<Group> deleteGroup(Integer id);

    Result<Group> updateGroup(GroupVo groupVo) throws CoreException;

    Result<PageResponse<List<Group>>> queryGroup(Group group);

    Result<Group> allocateRole(GroupVo groupVo) throws CoreException;
}
