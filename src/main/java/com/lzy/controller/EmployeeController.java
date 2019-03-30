package com.lzy.controller;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleSelectSubqueryTableSource;
import com.lzy.domain.Employee;
import com.lzy.domain.AjaxResult;
import com.lzy.domain.PageListResult;
import com.lzy.domain.PageNum;
import com.lzy.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @ResponseBody
    @RequestMapping("/save")
    public AjaxResult save(Employee employee) {
        employee.setState(true);
        AjaxResult ajaxResult = new AjaxResult();
        try {
            employeeService.save(employee);
            ajaxResult.setMsg("修改成功");
            ajaxResult.setSuccessful(true);

        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setMsg("修改失败");
            ajaxResult.setSuccessful(false);
        } finally {
            return ajaxResult;
        }


    }

    @ResponseBody
    @RequestMapping("/getEmployeeList")
    public PageListResult getAllEmployee(PageNum pageNum) {

        System.out.println("来了");
        System.out.println(pageNum);
        PageListResult pageList = employeeService.getEmployeeList(pageNum);
        return  pageList;

    }

    @ResponseBody
    @RequestMapping("/delete")
    public AjaxResult delete(long id) {
        AjaxResult AjaxResult = new AjaxResult();
        try {
            employeeService.delete(id);
            AjaxResult.setMsg("修改成功");
            AjaxResult.setSuccessful(true);


        } catch (Exception e) {
            AjaxResult.setMsg("修改失败");
            AjaxResult.setSuccessful(false);
            employeeService.delete(id);

        } finally {
            return AjaxResult;
        }

    }


    @ResponseBody
    @RequestMapping("/update")
    public AjaxResult updateEmplyee(Employee employee) {
        employee.setState(true);
        AjaxResult ajaxResult = new AjaxResult();
        try {
        employeeService.update(employee);
        ajaxResult.setSuccessful(true);

        ajaxResult.setMsg("修改成功");


        }catch (Exception e)
        {
         e.printStackTrace();
        ajaxResult.setMsg("修改失败");
        ajaxResult.setSuccessful(false);
        }finally {
        return ajaxResult;
        }

    }
    @ResponseBody
    @RequestMapping("/updateState")
    public AjaxResult updateState(long id)
    {

        AjaxResult ajaxResult = new AjaxResult();
        try {
            employeeService.updateState(id);
            ajaxResult.setSuccessful(true);

            ajaxResult.setMsg("修改成功");


        }catch (Exception e)
        {
            e.printStackTrace();
            ajaxResult.setMsg("修改失败");
            ajaxResult.setSuccessful(false);
        }finally {
            return ajaxResult;
        }
    }
}
