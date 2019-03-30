package com.lzy.mapper;


import com.lzy.domain.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleMapper {

   void saveRole(Role role);

   void insertInfoRoleAndPermission(@Param("rid") Long rid, @Param("pid") Long pid);

   List<Role> getRoleList();


   void deleteRelationWitwhPermission(@Param("rid") long rid);

   void addRelationWithPermission(@Param("rid") long rid,@Param("pid") long pid);

   void updateRoleByRid(Role role);

    void deleteRoleById(@Param("rid") long rid);

    List<Long> getRoleId(@Param("id") long id);
}
