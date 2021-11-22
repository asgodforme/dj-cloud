package com.dj.cloud.user.service.impl;

import com.dj.cloud.common.exception.CoreException;
import com.dj.cloud.common.utils.BeanUtils;
import com.dj.cloud.common.vo.GroupVo;
import com.dj.cloud.common.vo.PageResponse;
import com.dj.cloud.common.vo.Result;
import com.dj.cloud.user.entity.Group;
import com.dj.cloud.user.entity.Role;
import com.dj.cloud.user.entity.User;
import com.dj.cloud.user.repository.GroupRepository;
import com.dj.cloud.user.repository.RoleRepository;
import com.dj.cloud.user.repository.UserRepository;
import com.dj.cloud.user.service.GroupService;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private Function<GroupVo, Group> TO_GROUP_FROM_GROUPVO = groupVo -> {
        Group group = new Group();
        org.springframework.beans.BeanUtils.copyProperties(groupVo, group);
        return group;
    };

    private Function<GroupVo, List<User>> TO_USERS_FROM_GROUPVO = groupVo
            -> ArrayUtils.isNotEmpty(groupVo.getUserIds()) ?
            userRepository.findAllById(Arrays.asList(groupVo.getUserIds())):
            new ArrayList<>();

    private Function<GroupVo, List<Role>> TO_ROLES_FROM_GROUPVO = groupVo
            -> ArrayUtils.isNotEmpty(groupVo.getRoleIds()) ?
            roleRepository.findAllById(Arrays.asList(groupVo.getRoleIds())):
            new ArrayList<>();

    @Override
    public Result<Group> addGroup(GroupVo groupVo) throws CoreException {
        Group group = TO_GROUP_FROM_GROUPVO.apply(groupVo);
        group.setUsers(TO_USERS_FROM_GROUPVO.apply(groupVo));
        return Result.newResult(groupRepository.save(group));
    }

    @Override
    public Result<Group> deleteGroup(Integer id) {
        groupRepository.deleteById(id);
        return Result.newResult(new Group());
    }

    @Override
    public Result<Group> updateGroup(GroupVo groupVo) throws CoreException {
        Group origin = groupRepository.findById(groupVo.getId()).orElseThrow(() -> new CoreException("group not exist", "群组不存在"));
        Set<Role> roles = origin.getRoles();
        Map<String, Object> originMap = BeanUtils.toMap(origin);
        Map<String, Object> updateOriginMap = BeanUtils.toMap(TO_GROUP_FROM_GROUPVO.apply(groupVo));
        originMap.putAll(updateOriginMap);
        Group group = BeanUtils.toObject(originMap, Group.class);
        group.setUsers(TO_USERS_FROM_GROUPVO.apply(groupVo));
        group.setRoles(roles);

        return Result.newResult(groupRepository.save(group));
    }

    @Override
    public Result<PageResponse<List<Group>>> queryGroup(Group group) {
        Pageable pageable = PageRequest.of(group.getCurrent() - 1, group.getPageSize());
        PageImpl<Group> page = (PageImpl<Group>) groupRepository.findAll(Example.of(group), pageable);
        return Result.newResult(PageResponse.of(page.getTotalElements(), page.getContent()), "page");
    }

    @Override
    public Result<Group> allocateRole(GroupVo groupVo) throws CoreException {
        if (groupVo.getRoleIds() == null || groupVo.getRoleIds().length == 0) {
            throw new CoreException("please choose role", "请给群组选择角色");
        }
        Group origin = groupRepository.findById(groupVo.getId()).orElseThrow(() -> new CoreException("group not exist", "群组不存在"));
        List<User> users = origin.getUsers();
        Map<String, Object> originMap = BeanUtils.toMap(origin);
        Map<String, Object> updateOriginMap = BeanUtils.toMap(TO_GROUP_FROM_GROUPVO.apply(groupVo));
        originMap.putAll(updateOriginMap);
        Group group = BeanUtils.toObject(originMap, Group.class);
        group.setUsers(users);
        group.setRoles(TO_ROLES_FROM_GROUPVO.apply(groupVo).stream().collect(Collectors.toSet()));
        System.out.println(group);
        return Result.newResult(groupRepository.save(group));
    }
}
