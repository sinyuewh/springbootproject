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
    NOUSER(101, "当前用户不存在"),
    ;

    private Integer code;
    private String message;

    ErrorEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
