package com.lzy.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzy.domain.AjaxResult;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class FromAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        AjaxResult ajaxRes = new AjaxResult();
        ajaxRes.setSuccessful(false);
        if(e!=null)
        {
            String name =e.getClass().getName();
            if(name.equals(UnknownAccountException.class.getName())){
                /*没有帐号*/
                ajaxRes.setMsg("帐号不正确");
            } else if(name.equals(IncorrectCredentialsException.class.getName())){
                /*密码错误*/
                ajaxRes.setMsg("密码不正确");
            }else {
                /*未知异常*/
                ajaxRes.setMsg("未知错误");
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String s = null;
        try {
            s = objectMapper.writeValueAsString(ajaxRes);
        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
        }
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().print(s);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return false;
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setSuccessful(true);
        ajaxResult.setMsg("登录成功");
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(ajaxResult);
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(s);
        return false;
    }
}
