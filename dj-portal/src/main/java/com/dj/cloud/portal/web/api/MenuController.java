package com.dj.cloud.portal.web.api;

import com.dj.cloud.portal.service.MenuService;
import com.dj.cloud.portal.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/getMenus")
    public List<MenuVo> getMenus() {
//        List<Map<String, Object>> list = new ArrayList<>();
//        Map<String, Object> map = new HashMap<>();
//        map.put("path", "/welcome");
//        map.put("name", "welcome");
//        map.put("icon", "smile");
//        map.put("component", "Welcome");
//        list.add(map);
//
//        map = new HashMap<>();
//        map.put("path", "/admin");
//        map.put("name", "admin");
//        map.put("icon", "smile");
//        map.put("component", "Admin");
//        List<Map<String, Object>> subList = new ArrayList<>();
//        map.put("routes", subList);
//        list.add(map);
//
//        map = new HashMap<>();
//        map.put("path", "/admin/sub-page");
//        map.put("name", "sub-page");
//        map.put("icon", "smile");
//        map.put("component", "Welcome");
//        subList.add(map);
//
//        return list;
        return menuService.getMenus();
    }
}
