package com.jsj.webapi.dataobject.sysframe;

/**
 * Created by jinshouji on 2018/4/30.
 */

import lombok.Data;

import javax.persistence.*;

/**
 * @author ：jinshouji
 * @create ：2018-04-25 18:03
 * @remark:  框架系统表--权限定义表
 **/
@Data
@Entity
@Table(name="Permissions")
public class Permissions {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;                         //数据id

    @Column(name="permissionsId",length=50,unique=true,nullable=false)
    private String permissionsId;           //权限Id （通过表名+“：”+功能名 如 users:search 表示用户表（查询）

    @Column(name="permissionsName",length=50,nullable=false)
    private String permissionsName;       //权限名称

    @Column(name="parentId")
    private int parentId;                   //父权限ID

    @Column(name="num",length=50)
    private String num;                     //排序

    @Column(name="remark",length=200)
    private String remark;                 //权限说明

}
