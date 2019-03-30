package com.lzy.mapper;

import com.lzy.domain.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  PermissionMapper {

    List<Permission> getPermission();

    List<Permission> getPermissionById(@Param("rid") Long rid);
}
