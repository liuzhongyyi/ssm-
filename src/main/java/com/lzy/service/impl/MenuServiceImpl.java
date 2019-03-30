package com.lzy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lzy.domain.Menu;
import com.lzy.domain.PageListResult;
import com.lzy.domain.PageNum;
import com.lzy.mapper.MenuMapper;
import com.lzy.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper mapper;
    @Override
    public PageListResult getMenuList(PageNum pageNum) {

        Page<Object> pageHelper = PageHelper.startPage(pageNum.getPage(), pageNum.getRows());
        PageListResult pageListResult = new PageListResult();
        List<Menu> menuList = mapper.getMenuList();
        pageListResult.setRows(menuList);
        pageListResult.setTotal(pageHelper.getTotal());
        return pageListResult;
    }

    @Override
    public List<Menu> getMenuListParent() {
        return mapper.getMenuList();
    }

    @Override
    public void saveMenu(Menu menu) {
         mapper.saveMenu(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        mapper.updateMenu(menu);
    }
}
