package com.dj.cloud.user.service;

import com.dj.cloud.common.exception.CoreException;
import com.dj.cloud.common.vo.PageResponse;
import com.dj.cloud.common.vo.Result;
import com.dj.cloud.user.entity.Permission;

import java.util.List;

public interface PermissionService {

    Result<Permission> updatePermission(Permission permission) throws CoreException;

    Result<PageResponse<List<Permission>>> queryPermission(Permission permission);
}
