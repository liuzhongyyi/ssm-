package com.lzy.service.impl;

import com.lzy.domain.Permission;
import com.lzy.mapper.PermissionMapper;
import com.lzy.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public List<Permission> getPermissonList() {
        List<Permission> permission = permissionMapper.getPermission();
        return permission;
    }

    @Override
    public List<Permission> getPermissonById(long rid) {
        List<Permission> permissions = permissionMapper.getPermissionById(rid);
        return permissions;
    }
}
