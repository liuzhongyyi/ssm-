package com.lzy.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzy.domain.AjaxResult;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;


@Controller
public class PageLocation {

    @RequiresPermissions("employee:index")
    @RequestMapping(value = "/employee")
    public String employee()
    {
        return "employee";
    }

    @RequestMapping(value = "/menu")
    public String menu()
    {
        return "menu";
    }

    @RequestMapping(value = "/role")
    public String role()
    {
        return "role";
    }

    @ExceptionHandler(AuthorizationException.class)
    public void handleAuthorization(HandlerMethod handlerMethod, HttpServletResponse httpServletResponse) throws Exception {
        ResponseBody methodAnnotation = handlerMethod.getMethodAnnotation(ResponseBody.class);
        if(methodAnnotation!=null)
        {
            AjaxResult ajaxResult = new AjaxResult();
            httpServletResponse.setCharacterEncoding("utf-8");
            ajaxResult.setMsg("没有权限操作");
            ajaxResult.setSuccessful(false);
            httpServletResponse.getWriter().print(new ObjectMapper().writeValueAsString(ajaxResult));
        }else {
            httpServletResponse.sendRedirect("/nopermission.jsp");
        }

    }
}

