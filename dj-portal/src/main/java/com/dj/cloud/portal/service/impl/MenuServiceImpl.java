package com.dj.cloud.portal.service.impl;

import com.dj.cloud.portal.entity.Menu;
import com.dj.cloud.portal.repository.MenuRepository;
import com.dj.cloud.portal.service.MenuService;
import com.dj.cloud.portal.vo.MenuVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    /**
     * 将菜单pojo转换了菜单vo
     */
    Function<Menu, MenuVo> MENU_TO_MENUVO = menu -> {
        MenuVo menuVo = new MenuVo();
        BeanUtils.copyProperties(menu, menuVo);
        return menuVo;
    };

    @Override
    public List<MenuVo> getMenus() {
        //  查询所有菜单
        List<Menu> menus = menuRepository.findAll();
        //  拿到父菜单
        List<MenuVo> mainMenus = menus.stream()
                .filter(menu -> menu.getParentId() == null)
                .filter(Menu::isDisplay)
                .map(MENU_TO_MENUVO::apply)
                .collect(Collectors.toList());
        // 拿到子菜单
        List<Menu> subMenus = menus.stream()
                .filter(menu -> menu.getParentId() != null)
                .collect(Collectors.toList());
        // 排序子菜单
        Collections.sort(subMenus, (a, b) -> a.getDisplaySeq() - b.getDisplaySeq());

        // 整合前端需要的菜单
        mainMenus.stream().forEach(menuVo -> menuVo.setRoutes(
                        subMenus.stream()
                                .filter(subMenu -> menuVo.getId() == subMenu.getParentId())
                                .map(MENU_TO_MENUVO::apply)
                                .collect(Collectors.toList())));
        return mainMenus;
    }
}
