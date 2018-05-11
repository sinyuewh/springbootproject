package com.jsj.webapi.dataobject;
/**
 * Created by jinshouji on 2018/4/25.
 */

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ：jinshouji
 * @create ：2018-04-25 18:03
 * @remark:  课程实体类
 **/

@Data
@Entity
@Table(name = "Course")
public class Course implements Serializable {

    private static final long serialVersionUID = 2235494930046549176L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    /** 课程编号. */
    @Column(length = 50,nullable = false,unique = true)
    private String cno;

    /** 课程名称. */
    @Column(length = 50,nullable = false)
    private String cname;

    /** 备注. */
    private String remark;

}
