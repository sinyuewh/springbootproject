package com.jsj.webapi.enums;

import com.jsj.common.utils.CodeEnum;
import lombok.Getter;

/**
 * @author ：jinshouji
 * @create ：2018-04-25 14:42
 **/

@Getter
public enum ErrorEnum implements CodeEnum {
    REGISTER_FAIL(100, "注册失败"),

    NOUSER(101,"用户名或密码不正确"),
    NOLOIN(102,"没有登录！"),
    ERRORUSER(103,"用户名或密码为空！"),

    NOACCESS(403,"无权访问！"),
    NOPAGE(400,"当前访问的资源不存在！")
    ;

    private Integer code;
    private String message;

    ErrorEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
