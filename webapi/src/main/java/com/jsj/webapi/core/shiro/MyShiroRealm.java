package com.jsj.webapi.core.shiro;/**
 * Created by jinshouji on 2018/5/14.
 */

import com.jsj.webapi.dataobject.sysframe.Users;
import com.jsj.webapi.service.MemberService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ：jinshouji
 * @create ：2018-05-14 11:52
 * @Remark ：Shiro的身份验证的自定义类
 **/

public class MyShiroRealm extends AuthorizingRealm
{
    Map<String,String> userMap= new HashMap<String,String>();
    {
        //模拟数据库中的用户和密码数据
        userMap.put("jinshouji","123456");
        userMap.put("linmin","7654321");
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MemberService memberService;

    /**
     * 认证信息.(身份验证)
     * :
     * Authentication 是用来验证用户身份
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("前台登录认证：CustomShiroRealm.doGetAuthenticationInfo()");
        //1) 从主体传过来的信息获得用户名
        String username=(String)token.getPrincipal();

        //2）通过用户名到数据库中获取密码（利用数据库的操作）
        String password=getPasswordByUserName(username);
        if(password==null)
        {
            return null;
        }

        Users user1=new Users();
        user1.setUserName(username);
        user1.setTrueName("金寿吉");

        SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(user1,password,username);
        return simpleAuthenticationInfo;  //返回用户登陆的权限信息（用户名/密码/真实姓名）
    }

    /**
     * 此方法调用  hasRole,hasPermission的时候才会进行回调.
     *
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，
     * 调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
       /*
        * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
        * 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
        * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
        * 缓存过期之后会再次执行。
        */
        //得到用户的登录ID
        Users  username=(Users )principals.getPrimaryPrincipal();

        //构建用户的roles数据
        Set<String> role=new HashSet<String>();
        role.add("admin");
        role.add("guest");

        //构建用户的权限数据
        Set<String> permission = new HashSet<String>();
        if(username.getUserName().equals("jinshouji")) {
            permission.add("user:delete");
            permission.add("user:admin");
        }

        //返回权限的数据
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(role);
        simpleAuthorizationInfo.setStringPermissions(permission);

        return simpleAuthorizationInfo;
    }


    //-----------------------------------------------------------------------------------------------------------------
    private String getPasswordByUserName(String username) {
        return this.userMap.get(username);
    }

}
