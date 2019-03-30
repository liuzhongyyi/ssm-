package com.lzy.controller;

import com.lzy.domain.Menu;
import com.lzy.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class testContorller {
        @Autowired
        private MenuMapper menuMapper;

       @RequestMapping("test")
        public void getList()
        {
            List<Menu> menuList = menuMapper.getMenuList();
            System.out.println(menuList);
        }
}
