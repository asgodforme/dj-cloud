package com.dj.cloud.user.service.impl;

import com.dj.cloud.common.exception.CoreException;
import com.dj.cloud.common.vo.GroupVo;
import com.dj.cloud.common.vo.PageResponse;
import com.dj.cloud.common.vo.Result;
import com.dj.cloud.user.entity.Group;
import com.dj.cloud.user.entity.User;
import com.dj.cloud.user.repository.GroupRepository;
import com.dj.cloud.user.repository.UserRepository;
import com.dj.cloud.user.service.GroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    private Function<GroupVo, Group> TO_GROUP_FROM_GROUPVO = groupVo -> {
        Group group = new Group();
        BeanUtils.copyProperties(groupVo, group);
        return group;
    };

    private Function<GroupVo, List<User>> TO_USERS_FROM_GROUPVO = groupVo
            -> userRepository.findAllById(Arrays.asList(groupVo.getUserIds()));

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
        return Result.newResult(groupRepository.save(TO_GROUP_FROM_GROUPVO.apply(groupVo)));
    }

    @Override
    public Result<PageResponse<List<Group>>> queryGroup(Group group) {
        Pageable pageable = PageRequest.of(group.getCurrent() - 1, group.getPageSize());
        PageImpl<Group> page = (PageImpl<Group>) groupRepository.findAll(Example.of(group), pageable);
        return Result.newResult(PageResponse.of(page.getTotalElements(), page.getContent()), "page");
    }
}
