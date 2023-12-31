package com.dj.cloud.portal.web.api;

import com.dj.cloud.common.exception.CoreException;
import com.dj.cloud.common.vo.Result;
import com.dj.cloud.common.vo.UserVo;
import com.dj.cloud.portal.jwt.JwtUtils;
import com.dj.cloud.portal.jwt.Session;
import com.dj.cloud.user.entity.User;
import com.google.gson.Gson;
import com.dj.cloud.feign.client.DjUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/portal")
public class LoginController {

    @Autowired
    private DjUserClient djUserClient;

    @PostMapping("/login/account")
    public Result<Map<String, Object>> account(@RequestBody UserVo userVo) throws CoreException {
        System.out.println(userVo);
        Result<User> currentUser = djUserClient.getUser(userVo);
        Session session = new Session();
        session.setUserId(currentUser.getPayload().getId());
        session.setUserName(currentUser.getPayload().getUserName());
        String token = JwtUtils.generateToken(session);
        Map<String, Object> response = new HashMap<>();
        response.put("userName", session.getUserName());
        response.put("token", token);
        return Result.newResult(response);
    }

    @PostMapping("/outLogin")
    public Result outLogin() throws CoreException {
        // return new Result("ok", "account", "admin");
        return Result.newResult(null);
    }

    @GetMapping("/currentUser")
    public Map<String, Object> currentUser() {
        String res = "{\"success\":true,\"data\":{\"name\":\"Serati Ma\",\"avatar\":\"https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png\",\"userid\":\"00000001\",\"email\":\"antdesign@alipay.com\",\"signature\":\"海纳百川，有容乃大\",\"title\":\"交互专家\",\"group\":\"蚂蚁金服－某某某事业群－某某平台部－某某技术部－UED\",\"tags\":[{\"key\":\"0\",\"label\":\"很有想法的\"},{\"key\":\"1\",\"label\":\"专注设计\"},{\"key\":\"2\",\"label\":\"辣~\"},{\"key\":\"3\",\"label\":\"大长腿\"},{\"key\":\"4\",\"label\":\"川妹子\"},{\"key\":\"5\",\"label\":\"海纳百川\"}],\"notifyCount\":12,\"unreadCount\":11,\"country\":\"China\",\"access\":\"admin\",\"geographic\":{\"province\":{\"label\":\"浙江省\",\"key\":\"330000\"},\"city\":{\"label\":\"杭州市\",\"key\":\"330100\"}},\"address\":\"西湖区工专路 77 号\",\"phone\":\"0752-268888888\"}}";
        Gson gson = new Gson();
        return gson.fromJson(res, Map.class);
    }
}
