package com.lzy.controller;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleIfStatement;
import com.lzy.domain.*;
import com.lzy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Transactional
@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("/saveRole")
    public AjaxResult saveRole(Role role)
    {

        AjaxResult ajaxResult = new AjaxResult();
        try {
            roleService.saveRole(role);
            ajaxResult.setMsg("保存成功");
            ajaxResult.setSuccessful(true);
        }catch (Exception e)
        {
            ajaxResult.setMsg("保存失败");
            ajaxResult.setSuccessful(false);
        }finally {
            return ajaxResult;
        }

    }
    @ResponseBody
    @RequestMapping("/getRoleList")
    public PageListResult getRoleList(PageNum pageNum)
    {

        PageListResult roleList = roleService.getRoleList(pageNum);
        return roleList;
    }
    @ResponseBody
    @RequestMapping("/updateRole")
    public AjaxResult updateRole(Role role)
    {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            roleService.updateRole(role);
            ajaxResult.setSuccessful(true);
            ajaxResult.setMsg("修改成功");
        }catch (Exception e)
        {
            ajaxResult.setSuccessful(false);
            ajaxResult.setMsg("修改失败");
        }finally {
            return ajaxResult;
        }


    }
    @ResponseBody
    @RequestMapping("/deleteRole")
    public AjaxResult deleteRole(long rid)
    {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            roleService.deleteRoleById(rid);
            ajaxResult.setMsg("删除成功");
            ajaxResult.setSuccessful(true);
        }catch (Exception e)
        {
            ajaxResult.setMsg("删除失败");
            ajaxResult.setSuccessful(false);
        }finally {
            return ajaxResult;
        }

    }

    @ResponseBody
    @RequestMapping("/getRoles")
    public List<Role> getRoleList()
    {

        List<Role> roleList = roleService.getRoleList();
        return roleList;
    }

    @ResponseBody
    @RequestMapping("/getRoleId")
    public List<Long> getRoleId(long id)
    {
        List<Long> roleId = roleService.getRoleId(id);
        return roleId;
    }

}
