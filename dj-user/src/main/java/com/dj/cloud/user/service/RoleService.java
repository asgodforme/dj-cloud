package com.dj.cloud.user.service;

import com.dj.cloud.common.exception.CoreException;
import com.dj.cloud.common.vo.PageResponse;
import com.dj.cloud.common.vo.Result;
import com.dj.cloud.user.entity.Role;

import java.util.List;

public interface RoleService {

    Result<Role> addRole(Role role);

    Result<Role> deleteRole(Integer id);

    Result<Role> updateRole(Role role) throws CoreException;

    Result<PageResponse<List<Role>>> queryRole(Role role);
}
