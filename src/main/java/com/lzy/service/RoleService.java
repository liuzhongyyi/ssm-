package com.lzy.service;

import com.lzy.domain.PageListResult;
import com.lzy.domain.PageNum;
import com.lzy.domain.Role;

import java.util.List;

public interface RoleService {

    public void saveRole(Role role);

    PageListResult getRoleList(PageNum pageNum);


    void updateRole(Role role);

    void deleteRoleById(long rid);

    List<Role> getRoleList();

    List<Long> getRoleId(long id);
}
