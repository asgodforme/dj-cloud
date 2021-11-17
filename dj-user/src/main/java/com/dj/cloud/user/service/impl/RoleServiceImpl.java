package com.dj.cloud.user.service.impl;

import com.dj.cloud.common.exception.CoreException;
import com.dj.cloud.common.vo.PageResponse;
import com.dj.cloud.common.vo.Result;
import com.dj.cloud.user.entity.Role;
import com.dj.cloud.user.repository.RoleRepository;
import com.dj.cloud.user.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Result<Role> addRole(Role role) {
        return Result.newResult(roleRepository.save(role));
    }

    @Override
    public Result<Role> deleteRole(Integer id) {
        roleRepository.deleteById(id);
        return Result.newResult(new Role(id));
    }

    @Override
    public Result<Role> updateRole(Role role) throws CoreException {
        Role localSystemInfo = roleRepository.findById(role.getId())
                .orElseThrow(() -> new CoreException("role info not exist", "角色信息不存在"));
        BeanUtils.copyProperties(localSystemInfo, role);
        return Result.newResult(roleRepository.save(role));
    }

    @Override
    public Result<PageResponse<List<Role>>> queryRole(Role role) {
        Pageable pageable = PageRequest.of(role.getCurrent() - 1, role.getPageSize());
        PageImpl<Role> page = (PageImpl<Role>) roleRepository.findAll(Example.of(role), pageable);
        List<Role> roles = page.getContent().stream()
                .map(Role::getId)
                .map(roleRepository::findById)
                .map(Optional::get)
                .collect(Collectors.toList());
        return Result.newResult(PageResponse.of(page.getTotalElements(), roles), "page");
    }
}
