package com.lzy.service.impl;

import com.lzy.domain.Department;
import com.lzy.mapper.DepartmentMapper;
import com.lzy.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


import java.util.List;
@Transactional
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public List<Department> getDepartmentList() {

        List<Department> departmentList = departmentMapper.getDepartmentList();
        return  departmentList;
    }
}
