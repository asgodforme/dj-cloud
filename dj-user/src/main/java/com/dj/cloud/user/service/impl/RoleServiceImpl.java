package com.dj.cloud.user.service.impl;

import com.dj.cloud.common.exception.CoreException;
import com.dj.cloud.common.vo.PageResponse;
import com.dj.cloud.common.vo.Result;
import com.dj.cloud.common.vo.RoleVo;
import com.dj.cloud.user.entity.Permission;
import com.dj.cloud.user.entity.Role;
import com.dj.cloud.user.repository.PermissionRepository;
import com.dj.cloud.user.repository.RoleRepository;
import com.dj.cloud.user.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    private Function<RoleVo, Role> TO_ROLEVO_FROM_ROLE = roleVo -> {
        Role role = new Role();
        BeanUtils.copyProperties(roleVo, role);
        return role;
    };

    private Function<RoleVo, List<Permission>> TO_PERMISSIONS_FROM_ROLE = roleVo
            -> permissionRepository.findAllById(Arrays.asList(roleVo.getPermissionIds()));

    @Override
    public Result<Role> addRole(RoleVo roleVo) throws CoreException {
        if (roleVo.getPermissionIds() == null || roleVo.getPermissionIds().length == 0) {
            throw new CoreException("role's permissions is empty", "请给角色分配权限");
        }

        Role role = TO_ROLEVO_FROM_ROLE.apply(roleVo);
        List<Permission> permissions = TO_PERMISSIONS_FROM_ROLE.apply(roleVo);
        role.setPermissions(permissions);
        return Result.newResult(roleRepository.save(role));
    }

    @Override
    public Result<Role> deleteRole(Integer id) {
        roleRepository.deleteById(id);
        return Result.newResult(new Role(id));
    }

    @Override
    public Result<Role> updateRole(RoleVo roleVo) throws CoreException {
        if (roleVo.getPermissionIds() == null || roleVo.getPermissionIds().length == 0) {
            throw new CoreException("role's permissions is empty", "请给角色分配权限");
        }

        Role role = TO_ROLEVO_FROM_ROLE.apply(roleVo);
        List<Permission> permissions = TO_PERMISSIONS_FROM_ROLE.apply(roleVo);
        role.setPermissions(permissions);

        return Result.newResult(roleRepository.save(role));
    }

    @Override
    public Result<PageResponse<List<Role>>> queryRole(RoleVo roleVo) {
        Role role = TO_ROLEVO_FROM_ROLE.apply(roleVo);

        if (roleVo.getPermissionIds() == null || roleVo.getPermissionIds().length == 0) {
            Pageable pageable = PageRequest.of(role.getCurrent() - 1, role.getPageSize());
            PageImpl<Role> page = (PageImpl<Role>) roleRepository.findAll(Example.of(role), pageable);
            List<Role> roles = page.getContent().stream()
                    .map(Role::getId)
                    .map(roleRepository::findById)
                    .map(Optional::get)
                    .collect(Collectors.toList());
            return Result.newResult(PageResponse.of(page.getTotalElements(), roles), "page");
        } else {
            List<Permission> queryPermission = TO_PERMISSIONS_FROM_ROLE.apply(roleVo);
            role.setPermissions(queryPermission);
            List<Role> roles = roleRepository.findAll(Example.of(role));

            roles = roles.stream()
                    .filter(r -> r.getPermissions().stream().filter(f -> queryPermission.contains(f)).count() > 0)
                    .collect(Collectors.toList());

            int startIndex = (role.getCurrent() - 1) * role.getPageSize();
            int endIndex = role.getCurrent()  * role.getPageSize();
            endIndex = endIndex > roles.size() ? roles.size() : endIndex - 1;
            long total = roles.size();

            return Result.newResult(PageResponse.of(total, roles.subList(startIndex, endIndex)), "page");
        }
    }

}
