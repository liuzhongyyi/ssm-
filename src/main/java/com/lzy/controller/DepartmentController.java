package com.lzy.controller;

import com.lzy.domain.Department;
import com.lzy.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @ResponseBody
    @RequestMapping("/departmentList")
    List<Department> getDepartmentList()
    {
        List<Department> departmentList = departmentService.getDepartmentList();
        return  departmentList;
    }
}
