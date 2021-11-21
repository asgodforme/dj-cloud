package com.dj.cloud.user.service;

import com.dj.cloud.common.exception.CoreException;
import com.dj.cloud.common.vo.PageResponse;
import com.dj.cloud.common.vo.Result;
import com.dj.cloud.common.vo.UserVo;
import com.dj.cloud.user.entity.User;

import java.util.List;

public interface UserService {

    Result<User> addUser(UserVo userVo) throws CoreException;

    Result<User> deleteUser(Integer id);

    Result<PageResponse<List<User>>> queryUser(User user);
}
