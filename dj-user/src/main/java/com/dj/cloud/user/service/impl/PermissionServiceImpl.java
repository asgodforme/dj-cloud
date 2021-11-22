package com.dj.cloud.user.service.impl;

import com.dj.cloud.common.exception.CoreException;
import com.dj.cloud.common.utils.BeanUtils;
import com.dj.cloud.common.vo.PageResponse;
import com.dj.cloud.common.vo.Result;
import com.dj.cloud.user.entity.Permission;
import com.dj.cloud.user.repository.PermissionRepository;
import com.dj.cloud.user.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Result<Permission> updatePermission(Permission permission) throws CoreException {
        Permission origin = permissionRepository.findById(permission.getId())
                .orElseThrow(() ->  new CoreException("system info not exist", "权限信息不存在"));
        Map<String, Object> originMap = BeanUtils.toMap(origin);
        originMap.putAll(BeanUtils.toMap(permission));
        return Result.newResult(permissionRepository.save(BeanUtils.toObject(originMap, Permission.class)));
    }

    @Override
    public Result<PageResponse<List<Permission>>> queryPermission(Permission permission) {
        if (permission.getCurrent() == null || permission.getPageSize() == null) {
            List<Permission> all = permissionRepository.findAll(Example.of(permission));
            return Result.newResult(PageResponse.of(all.size(), all));
        }
        Pageable pageable = PageRequest.of(permission.getCurrent() - 1, permission.getPageSize());
        PageImpl<Permission> page = (PageImpl<Permission>) permissionRepository.findAll(Example.of(permission), pageable);
        return Result.newResult(PageResponse.of(page.getTotalElements(), page.getContent()), "page");
    }
}
