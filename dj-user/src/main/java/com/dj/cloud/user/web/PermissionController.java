package com.dj.cloud.user.web;


import com.dj.cloud.common.exception.CoreException;
import com.dj.cloud.common.vo.PageResponse;
import com.dj.cloud.common.vo.Result;
import com.dj.cloud.user.entity.Permission;
import com.dj.cloud.user.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/updatePermission")
    public Result<Permission> updatePermission(@RequestBody Permission Permission) throws CoreException {
        return permissionService.updatePermission(Permission);
    }

    @GetMapping("/queryPermission")
    public Result<PageResponse<List<Permission>>> queryPermission(Permission Permission) {
        return permissionService.queryPermission(Permission);
    }
}
