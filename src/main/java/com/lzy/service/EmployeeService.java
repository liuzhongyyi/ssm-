package com.lzy.service;

import com.lzy.domain.Employee;
import com.lzy.domain.PageListResult;
import com.lzy.domain.PageNum;

import java.util.ArrayList;
import java.util.List;

public interface EmployeeService {
    public void save(Employee employee);

    public PageListResult getEmployeeList(PageNum pageNum);

    public void delete(Long id);

    void update(Employee employee);

    void updateState(long id);

   Employee getInfoByEmployeeName(String username);

   List<String> getRoleByEmployeeId(Long id);

    List<String> getPermissionByEmployeeId(Long id);
}
