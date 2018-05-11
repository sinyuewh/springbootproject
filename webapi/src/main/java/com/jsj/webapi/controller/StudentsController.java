package com.jsj.webapi.controller;

import com.jsj.common.bean.HttpResult;
import com.jsj.common.config.RedisConfig;
import com.jsj.common.utils.MyStringUtil;
import com.jsj.common.utils.HttpResultUtil;
import com.jsj.webapi.dataobject.Students;
import com.jsj.webapi.service.StudentsService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jinshouji on 2018/4/25.
 */
@RestController
@RequestMapping("/webapi/students/")
@Slf4j
public class StudentsController
{
    @Autowired
    private  RedisConfig redisConfig;

    @Autowired
    private StudentsService studentsService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    //根据姓名和学号，返回学生列表 StudentsListDTO
    @PostMapping("/list")
    public HttpResult list(String sname, String sno, @RequestParam(value = "page", defaultValue = "1") Integer page,
                           @RequestParam(value = "size", defaultValue = "10") Integer size)
    {
        PageRequest request = new PageRequest(page, size);
        Page p1= studentsService.getSearchResult(sname,sno,page,size);
        return HttpResultUtil.success(p1);
    }

    @PostMapping("/test")
    public HttpResult test()
    {
        stringRedisTemplate.opsForValue().set("username","jinshouji",5);
        stringRedisTemplate.opsForValue().set("password","123456");

        return HttpResultUtil.success(redisConfig);
    }
}
