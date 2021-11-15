package com.dj.cloud.portal.service;

import com.dj.cloud.object.vo.Result;
import com.dj.cloud.portal.vo.MenuVo;

import java.util.List;

public interface MenuService {

    /**
     * 获取所有的菜单
     * @return
     */
    Result<List<MenuVo>> getMenus();
}
