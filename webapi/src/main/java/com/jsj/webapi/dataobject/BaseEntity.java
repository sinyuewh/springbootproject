package com.jsj.webapi.dataobject;/**
 * Created by jinshouji on 2018/4/25.
 */

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ：jinshouji
 * @create ：2018-04-25 18:40
 * @remark : 项目的基础实体
 **/

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    //创建时间
    private Date createDate;

    //更新时间
    private Date modifyDate;

    //创建用户
    @Column(length = 50)
    private String createBy;

    //更新用户
    @Column(length = 50)
    private String updateBy;

    @PrePersist
    public void beforeAdd() {
        modifyDate = createDate = new Date();
    }

    @PreUpdate
    public void beforeModified() {
        modifyDate = new Date();
    }
}
