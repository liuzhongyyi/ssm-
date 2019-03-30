package com.lzy.mapper;

import com.lzy.domain.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper  {

    List<Menu> getMenuList();

    void saveMenu(Menu menu);

    void updateMenu(Menu menu);
}
