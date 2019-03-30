package com.lzy.controller;

import com.lzy.domain.Permission;
import com.lzy.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @ResponseBody
    @RequestMapping("/getPermissionList")
    public List<Permission> getPermission()
    {
        List<Permission> permissonList = permissionService.getPermissonList();
        return permissonList;

    }

    @ResponseBody
    @RequestMapping("/getPermisson")
    public List<Permission> getPermissonById(long rid)
    {
        System.out.println("rid"+rid);
        List<Permission> permissons = permissionService.getPermissonById(rid);
        System.out.println(permissons);
        return permissons;
    }


}
