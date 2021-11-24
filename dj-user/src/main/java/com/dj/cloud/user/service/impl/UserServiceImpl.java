package com.dj.cloud.user.service.impl;

import com.dj.cloud.common.exception.CoreException;
import com.dj.cloud.common.vo.PageResponse;
import com.dj.cloud.common.vo.Result;
import com.dj.cloud.common.vo.UserVo;
import com.dj.cloud.user.entity.User;
import com.dj.cloud.user.repository.UserRepository;
import com.dj.cloud.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private Function<UserVo, User> TO_USER_FROM_USERVO = userVo -> {
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        return user;
    };

    @Override
    public Result<User> addUser(UserVo userVo) throws CoreException {
        return Result.newResult(userRepository.save(TO_USER_FROM_USERVO.apply(userVo)));
    }

    @Override
    public Result<User> deleteUser(Integer id) {
        userRepository.deleteById(id);
        return Result.newResult(new User(id));
    }

    @Override
    public Result<PageResponse<List<User>>> queryUser(User user) {
        if (user.getCurrent() == null || user.getPageSize() == null) {
            List<User> all = userRepository.findAll(Example.of(user));
            return Result.newResult(PageResponse.of(all.size(), all), "");
        }
        Pageable pageable = PageRequest.of(user.getCurrent() - 1, user.getPageSize());
        PageImpl<User> page = (PageImpl<User>) userRepository.findAll(Example.of(user), pageable);
        return Result.newResult(PageResponse.of(page.getTotalElements(), page.getContent()), "page");
    }

    @Override
    public Result<User> getUser(UserVo userVo) throws CoreException {
        User user = TO_USER_FROM_USERVO.apply(userVo);
        List<User> users = userRepository.findAll(Example.of(user));
        if (CollectionUtils.isEmpty(users)) throw new CoreException("user not exist", "用户不存在");
        return Result.newResult(users.get(0));
    }
}
