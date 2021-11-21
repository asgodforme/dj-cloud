package com.dj.cloud.user.web;


import com.dj.cloud.common.exception.CoreException;
import com.dj.cloud.common.vo.PageResponse;
import com.dj.cloud.common.vo.Result;
import com.dj.cloud.common.vo.GroupVo;
import com.dj.cloud.user.entity.Group;
import com.dj.cloud.user.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/addGroup")
    public Result<Group> addGroup(@RequestBody GroupVo groupVo) throws CoreException {
        return groupService.addGroup(groupVo);
    }

    @PostMapping("/deleteGroup")
    public Result<Group> deleteGroup(@RequestBody Group group) {
        return groupService.deleteGroup(group.getId());
    }

    @PostMapping("/updateGroup")
    public Result<Group> updateGroup(@RequestBody GroupVo groupVo) throws CoreException {
        return groupService.updateGroup(groupVo);
    }

    @GetMapping("/queryGroup")
    public Result<PageResponse<List<Group>>> queryGroup(Group group) {
        return groupService.queryGroup(group);
    }
}
