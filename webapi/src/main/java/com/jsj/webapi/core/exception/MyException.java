package com.jsj.webapi.core.exception;
/**
 * Created by jinshouji on 2018/5/14.
 */

import com.jsj.webapi.enums.ErrorEnum;

/**
 * @author ：jinshouji
 * @create ：2018-05-14 17:33
 * @remark ：自定义异常
 **/

public class MyException extends RuntimeException {
    private  Integer code;
    public MyException(Integer code,String msg)
    {
        super(msg);
        this.code=code;
    }
    public MyException(ErrorEnum errcode)
    {
        super(errcode.getMessage());
        this.code=errcode.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
