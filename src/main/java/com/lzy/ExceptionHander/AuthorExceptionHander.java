package com.lzy.ExceptionHander;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzy.domain.AjaxResult;
import com.sun.tools.internal.ws.processor.model.Response;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.Scanner;
@ControllerAdvice
public class AuthorExceptionHander {
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
