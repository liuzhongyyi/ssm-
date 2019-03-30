package com.lzy.mapper;

import com.lzy.domain.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface EmployeeMapper {
    void  save(Employee employee);

    List<Employee> getEmployeeList();

   void deleteEmployee(Long id);

    void updateEmployee(Employee employee);

    void updateState(long id);

    void insertRaletionEmployyeeAndRole(@Param("id") long id, @Param("rid")long rid);

    void deleteRelationEmployeeWithRole(@Param("id") Long id);

    Employee getInfoByEmployeeName(@Param("username") String username);

    List<String> getRoleByEmployeeId(Long id);

    List<String> getPermissionByEmployeeId(Long id);
}
