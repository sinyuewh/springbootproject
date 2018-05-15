package com.jsj.webapi.dataobject.sysframe;

import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by jinshouji on 2018/4/30.
 */

/**
 * @author ：jinshouji
 * @create ：2018-04-25 18:03
 * @remark:  框架系统表--用户表
 **/

@Data
@Entity
@Table(name="Users")
public class Users implements Serializable
{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;                 //数据id

    @Column(name="userName",length=50,unique=true,nullable=false)
    private String userName;       //用户名

    @Column(name="password",length =50,nullable=false)
    private String password;        //登陆密码

    @Column(name="trueName",length = 50,nullable = false)
    private String trueName;        //真实姓名
}
