package com.jsj.common.utils;/**
 * Created by jinshouji on 2018/4/26.
 */

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;

/**
 * @author ：jinshouji
 * @create ：2018-04-26 13:41
 * 备注：字符串实用工具类
 **/

public class MyStringUtil {

    //用于加密和解密的字符串
    private static final String keyStr = "2235494930046549176L";

    //#region 字符串简单的加密和解密
    //字符串的简单加密和解密（加密2次，就是解密）
    public static String EnString(String strCryptThis,String strKey)
    {
        String strEncrypted = "";
        char iCryptChar, iKeyChar, iStringChar;
        int j = 0;
        for (int i = 0; i < strCryptThis.length(); i++)
        {
            iKeyChar = strKey.charAt(i);
            iStringChar = strCryptThis.charAt(i);

            iCryptChar = (char)(iKeyChar ^ iStringChar);
            strEncrypted = strEncrypted + iCryptChar;

            j++;
            if (j == strKey.length())
            {
                j = 0;
            }
        }
        return strEncrypted;
    }

    //字符串默认的加密和解密方法
    public static String EnString(String strCryptThis)
    {
        String str1 = EnString(strCryptThis, keyStr);
        return str1;
    }
    // #endregion

    //#region md5加密
    public static String md5(String text, String key) throws Exception {
        //加密后的字符串
        String encodeStr= DigestUtils.md5Hex(text + key);
        return encodeStr;
    }

    public static String md5(String text) throws Exception {
       return md5(text,keyStr);
    }

    public static boolean verifyMd5(String text, String key, String encodeStr) throws Exception {
        //根据传入的密钥进行验证
        String md5Text = md5(text, key);
        if(md5Text.equalsIgnoreCase(encodeStr))
        {
            return true;
        }
        return false;
    }

    public static boolean verifyMd5(String text, String encodeStr) throws Exception {
        return verifyMd5(text,keyStr,encodeStr);
    }
    //endregion

}
