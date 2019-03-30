package com.lzy.controller;

import com.lzy.domain.AjaxResult;
import com.lzy.domain.Menu;
import com.lzy.domain.PageListResult;
import com.lzy.domain.PageNum;
import com.lzy.mapper.MenuMapper;
import com.lzy.service.MenuService;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ResponseBody
    @RequestMapping("/menuList")
    PageListResult getMenuList(PageNum pageNum) {
        PageListResult menuList = menuService.getMenuList(pageNum);

        return menuList;
    }

    @ResponseBody
    @RequestMapping("/parentList")
    public List<Menu> MenuList() {
        List<Menu> menuList = menuService.getMenuList();
        return menuList;
    }

    @ResponseBody
    @RequestMapping("/saveMenu")
    public AjaxResult saveMenu(Menu menu) {

        AjaxResult ajaxResult = new AjaxResult();
        try {
           menuService.saveMenu(menu);
           ajaxResult.setSuccessful(true);
           ajaxResult.setMsg("保存成功");
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccessful(false);
            ajaxResult.setMsg("保存失败");

    }finally {
            return ajaxResult;
        }
    }
}