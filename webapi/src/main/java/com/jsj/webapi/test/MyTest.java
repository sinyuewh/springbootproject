package com.jsj.webapi.test;/**
 * Created by jinshouji on 2018/5/9.
 */

import com.jsj.common.utils.JsonUtil;
import com.jsj.webapi.dataobject.Students;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONValue;


/**
 * @author ：jinshouji
 * @create ：2018-05-09 16:37
 **/

public class MyTest {

    public static void main(String[] args)
    {
        Students s1=new Students();
        s1.setAddress("hust");
        s1.setSex("man");
        String s0=JSONValue.toJSONString(s1);

        Students s2=JSONValue.parse(s0,Students.class);

        Object obj1=JsonUtil.json2pojo(s0);
        System.out.println(s1.getSex());
        //System.out.println(s2.getSex());
    }
}
