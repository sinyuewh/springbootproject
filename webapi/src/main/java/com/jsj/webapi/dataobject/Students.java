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
 * @remark:  学生实体类
 **/

@Data
@Entity
//@Table(name = "Students")
public class Students implements Serializable {

    private static final long serialVersionUID = 8426417804872317599L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    /** 学号. */
    @Column(length = 50,nullable = false,unique = true)
    private String sno;

    /** 姓名. */
    @Column(length = 50,nullable = false)
    private String sname;

    /** 性别. */
    @Column(length = 10,nullable = false)
    private String sex;

    /** 生日. */
    private Date birthday;

    /** 地址. */
    @Column(length = 250)
    private String address;

    /** 备注 */
    private String remark;
}
