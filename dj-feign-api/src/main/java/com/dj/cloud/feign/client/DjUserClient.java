package com.dj.cloud.feign.client;

import com.dj.cloud.common.exception.CoreException;
import com.dj.cloud.common.vo.Result;
import com.dj.cloud.common.vo.UserVo;
import com.dj.cloud.user.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value="djuser")
public interface DjUserClient {

    @PostMapping("/api/user/getUser")
    Result<User> getUser(@RequestBody UserVo userVo) throws CoreException;
}
