package com.jsj.webapi.aspect;/**
 * Created by jinshouji on 2018/5/15.
 */

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author ：jinshouji
 * @create ：2018-05-15 15:58
 **/

@Aspect
@Component
public class HttpAspect {
    @Before("execution(public * com.jsj.webapi.controller.*.*(..))")
    public void log()
    {
        System.out.printf("OK!");
    }
}
