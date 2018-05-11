package com.jsj.webapi.dataobject.sysframe;

/**
 * Created by jinshouji on 2018/4/30.
 */

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author ：jinshouji
 * @create ：2018-04-25 18:03
 * @remark:  框架系统表--角色权限表
 **/
public class RolePermissions {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;                 //数据id

    @Column(name="roleName",length=50,nullable=false)
    private String roleName;

    @Column(name="permissionsId",length=50,nullable=false)
    private String permissionsId;           //权限Id （通过表名+“：”+功能名 如 users:search 表示用户表（查询）
}
