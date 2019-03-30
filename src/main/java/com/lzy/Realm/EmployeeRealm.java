package com.lzy.Realm;

import com.lzy.domain.Employee;
import com.lzy.domain.Permission;
import com.lzy.domain.Role;
import com.lzy.mapper.EmployeeMapper;
import com.lzy.service.EmployeeService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRealm extends AuthorizingRealm {
    @Autowired
    private EmployeeService employeeService;

    //认证的过程(登录过来验证)
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //获取主体的信息
        String username = (String) authenticationToken.getPrincipal();
        //查询有没有employeeL
        System.out.println("来了");
        Employee employeeInfo = employeeService.getInfoByEmployeeName(username);
        System.out.println(employeeInfo);
        if(employeeInfo==null)
        {
            return null;
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(employeeInfo, employeeInfo.getPassword(), this.getName());
        return simpleAuthenticationInfo;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //得到主体
       Employee employee = (Employee)principalCollection.getPrimaryPrincipal();
        List<String> roles = new ArrayList<>();
        List<String> permission = new ArrayList<>();
        if(employee.getAdmin())
        {
            permission.add("*:*");
        }else {
            /*查询对应的角色*/
            roles = employeeService.getRoleByEmployeeId(employee.getId());
            /*查询对应主体的权限*/
            permission =employeeService.getPermissionByEmployeeId(employee.getId());
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addRoles(permission);
            simpleAuthorizationInfo.addStringPermissions(permission);
            return simpleAuthorizationInfo;
        }


        return null;
    }


}
