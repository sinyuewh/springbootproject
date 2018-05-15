package com.jsj.webapi.controller;/**
 * Created by jinshouji on 2018/5/14.
 */

import com.jsj.common.bean.HttpResult;
import com.jsj.common.utils.HttpResultUtil;
import com.jsj.webapi.dataobject.sysframe.Users;
import com.jsj.webapi.enums.ErrorEnum;
import com.jsj.webapi.service.MemberService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author ：jinshouji
 * @create ：2018-05-14 14:35
 **/

@RestController
@RequestMapping("/member")
public class MemberController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public HttpResult doLogin(String username, String password) {
        if (null == username || username.length() == 0 || null == password || password.length() == 0) {
            return HttpResultUtil.error(ErrorEnum.ERRORUSER.getCode(),
                    ErrorEnum.ERRORUSER.getMessage());
        }

        //使用用户名和密码的凭证进行验证
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject currentUser = SecurityUtils.getSubject();
        boolean loginchk = false;
        try {
            logger.info("对用户[" + username + "]进行登录验证..验证开始");
            currentUser.login(token);
            logger.info("对用户[" + username + "]进行登录验证..验证通过");
            loginchk = true;
        } catch (UnknownAccountException uae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
        } catch (IncorrectCredentialsException ice) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
        } catch (LockedAccountException lae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
        } catch (AuthenticationException ae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
        }

        //验证是否登录成功
        if (currentUser.isAuthenticated()) {
            Session session = currentUser.getSession();
            //session.setAttribute("loginType", LoginEnum.CUSTOMER.toString());
            logger.info("前台用户[" + username + "]登录认证通过");
            return HttpResultUtil.success();
        } else {
            token.clear();
            return HttpResultUtil.error(ErrorEnum.ERRORUSER.getCode(),
                    ErrorEnum.ERRORUSER.getMessage());
        }
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public HttpResult logout() {
        try {
            System.out.println("MemberController.logout()");
            SecurityUtils.getSubject().logout();
            System.out.println("您已安全退出");
        }catch (Exception e){
            System.out.println(e);
        }
        return HttpResultUtil.success();
    }


    @RequestMapping(value = "/unauth")
    public HttpResult unauth()
    {
        return HttpResultUtil.error(100,"用户未登录");
    }


    @RequiresPermissions("user:admin")
    @RequestMapping(value = "/test")
    public HttpResult mytest()
    {
        return  HttpResultUtil.success("admin:test");
    }

    @RequestMapping(value = "/test1")
    public HttpResult mytest1()
    {
        Users user1= (Users)SecurityUtils.getSubject().getPrincipal();
        return  HttpResultUtil.success(user1);
    }
}
