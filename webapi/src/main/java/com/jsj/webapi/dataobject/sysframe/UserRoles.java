package com.jsj.webapi.dataobject.sysframe;

/**
 * Created by jinshouji on 2018/4/30.
 */

import lombok.Data;

import javax.persistence.*;

/**
 * @author ：jinshouji
 * @create ：2018-04-25 18:03
 * @remark:  框架系统表--用户角色表
 **/
@Data
@Entity
@Table(name="UserRoles")
public class UserRoles {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;                 //数据id

    @Column(name="userName",length=50,nullable=false)
    private String userName;       //用户名

    @Column(name="roleName",length=50,nullable=false)
    private String roleName;
}
