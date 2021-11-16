package com.dj.cloud.portal.web.api;

import com.dj.cloud.common.vo.Result;
import com.dj.cloud.portal.service.MenuService;
import com.dj.cloud.portal.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/portal")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/getMenus")
    public Result<List<MenuVo>> getMenus() {
        return menuService.getMenus();
    }
}
