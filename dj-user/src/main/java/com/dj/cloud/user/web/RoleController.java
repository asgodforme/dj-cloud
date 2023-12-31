package com.dj.cloud.user.web;


import com.dj.cloud.common.exception.CoreException;
import com.dj.cloud.common.vo.PageResponse;
import com.dj.cloud.common.vo.Result;
import com.dj.cloud.common.vo.RoleVo;
import com.dj.cloud.user.entity.Role;
import com.dj.cloud.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/addRole")
    public Result<Role> addRole(@RequestBody RoleVo roleVo) throws CoreException {
        return roleService.addRole(roleVo);
    }

    @PostMapping("/deleteRole")
    public Result<Role> deleteRole(@RequestBody Role role) {
        return roleService.deleteRole(role.getId());
    }

    @PostMapping("/updateRole")
    public Result<Role> updateRole(@RequestBody RoleVo roleVo) throws CoreException {
        return roleService.updateRole(roleVo);
    }

    @GetMapping("/queryRole")
    public Result<PageResponse<List<Role>>> queryRole(RoleVo roleVo) {
        return roleService.queryRole(roleVo);
    }
}
