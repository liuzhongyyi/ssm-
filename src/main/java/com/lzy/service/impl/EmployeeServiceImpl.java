package com.lzy.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lzy.domain.*;
import com.lzy.mapper.EmployeeMapper;
import com.lzy.service.EmployeeService;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public void save(Employee employee) {
        employeeMapper.save(employee);
        for (Role role : employee.getRoles()) {
            employeeMapper.insertRaletionEmployyeeAndRole(employee.getId(),role.getRid());
        }


    }

    @Override
    public PageListResult getEmployeeList(PageNum pageNum)
    {
        Page<Object> page = PageHelper.startPage(pageNum.getPage(), pageNum.getRows());
        List<Employee> employeeList = employeeMapper.getEmployeeList();
        PageListResult pageListResult = new PageListResult();
        pageListResult.setTotal(page.getTotal());
        pageListResult.setRows(employeeList);
        return pageListResult;


    }

    @Override
    public void delete(Long id) {
        employeeMapper.deleteEmployee(id);
    }

    @Override
    public void update(Employee employee) {
        employeeMapper.deleteRelationEmployeeWithRole(employee.getId());
        for (Role role : employee.getRoles()) {
            employeeMapper.insertRaletionEmployyeeAndRole(employee.getId(),role.getRid());
        }
        employeeMapper.updateEmployee(employee);
    }

    @Override
    public void updateState(long id) {
        employeeMapper.updateState(id);
    }

    @Override
    public Employee getInfoByEmployeeName(String username) {
        Employee infoByEmployeeName = employeeMapper.getInfoByEmployeeName(username);
        return infoByEmployeeName;
    }

    @Override
    public List<String> getRoleByEmployeeId(Long id) {
        List<String> roleByEmployeeId = employeeMapper.getRoleByEmployeeId(id);
        return roleByEmployeeId;
    }

    @Override
    public List<String> getPermissionByEmployeeId(Long id) {
        return  employeeMapper.getPermissionByEmployeeId(id);
    }



}
