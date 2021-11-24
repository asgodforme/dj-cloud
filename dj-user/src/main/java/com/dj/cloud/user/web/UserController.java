package com.dj.cloud.user.web;


import com.dj.cloud.common.exception.CoreException;
import com.dj.cloud.common.vo.PageResponse;
import com.dj.cloud.common.vo.Result;
import com.dj.cloud.common.vo.UserVo;
import com.dj.cloud.user.entity.User;
import com.dj.cloud.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public Result<User> addUser(@RequestBody UserVo userVo) throws CoreException {
        return userService.addUser(userVo);
    }

    @PostMapping("/deleteUser")
    public Result<User> deleteUser(@RequestBody User user) {
        return userService.deleteUser(user.getId());
    }

    @GetMapping("/queryUser")
    public Result<PageResponse<List<User>>> queryUser(User user) {
        return userService.queryUser(user);
    }

    @PostMapping("/getUser")
    public Result<User> getUser(@RequestBody UserVo userVo) throws CoreException {
        System.out.println(userVo);
        return userService.getUser(userVo);
    }
}
