package com.lzy.service;

import com.lzy.domain.Menu;
import com.lzy.domain.PageListResult;
import com.lzy.domain.PageNum;

import java.util.List;

public interface MenuService {

    PageListResult getMenuList(PageNum pageNum);

    List<Menu> getMenuListParent();

    void saveMenu(Menu menu);

    void updateMenu(Menu menu);
}
