package com.lzy.service;

import com.lzy.domain.Permission;

import java.util.List;

public interface PermissionService {

    public List<Permission> getPermissonList();

    List<Permission> getPermissonById(long rid);
}
