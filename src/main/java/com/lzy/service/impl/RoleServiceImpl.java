package com.lzy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lzy.domain.PageListResult;
import com.lzy.domain.PageNum;
import com.lzy.domain.Permission;
import com.lzy.domain.Role;
import com.lzy.mapper.RoleMapper;
import com.lzy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void saveRole(Role role) {
        //保存角色信息
        roleMapper.saveRole(role);
        for (Permission permission:role.getPermissions()) {
            roleMapper.insertInfoRoleAndPermission(role.getRid(),permission.getPid());
            
        }

    }

    @Override
    public PageListResult getRoleList(PageNum pageNum) {
        Page<Object> objects = PageHelper.startPage(pageNum.getPage(), pageNum.getRows());
        List<Role> roleList = roleMapper.getRoleList();
        PageListResult pageListResult = new PageListResult();
        pageListResult.setTotal(objects.getTotal());
        pageListResult.setRows(roleList);

        return pageListResult;
    }

    @Override
    public void updateRole(Role role) {
        //先打破对应的Permission的关系
        roleMapper.deleteRelationWitwhPermission(role.getRid());
        //重新建立关系
        for (Permission permission : role.getPermissions()) {

            roleMapper.addRelationWithPermission(role.getRid(),permission.getPid());
        }
        //保存角色的信息
        roleMapper.updateRoleByRid(role);

    }

    @Override
    public void deleteRoleById(long rid) {
        roleMapper.deleteRelationWitwhPermission(rid);
        roleMapper.deleteRoleById(rid);
    }

    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }

    @Override
    public List<Long> getRoleId(long id) {
        return roleMapper.getRoleId(id);
    }


}
