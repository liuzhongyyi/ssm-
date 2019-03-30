package com.lzy.service;

import com.lzy.domain.Menu;
import com.lzy.domain.PageListResult;
import com.lzy.domain.PageNum;

import java.util.List;

public interface MenuService {

    PageListResult getMenuList(PageNum pageNum);

    List<Menu> getMenuList();

    void saveMenu(Menu menu);
}
