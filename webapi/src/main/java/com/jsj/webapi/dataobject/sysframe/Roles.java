package com.jsj.webapi.dataobject.sysframe;

/**
 * Created by jinshouji on 2018/4/30.
 */

import lombok.Data;

import javax.persistence.*;

/**
 * @author ：jinshouji
 * @create ：2018-04-25 18:03
 * @remark:  框架系统表--角色表
 **/
@Data
@Entity
@Table(name="Roles")
public class Roles {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;                 //数据id

    @Column(name="roleName",length=50,unique=true,nullable=false)
    private String roleName;       //角色名称

    @Column(name="remark",length=200)
    private String remark;       //角色说明
}
