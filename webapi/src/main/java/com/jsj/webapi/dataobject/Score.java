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
 * @remark:  学生成绩表
 **/

@Data
@Entity
@Table(name = "Score")
public class Score implements Serializable {

    private static final long serialVersionUID = -2587802499125363766L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    /** 学生id. */
    @Column(nullable = false)
    private int sid;

    /** 课程id. */
    @Column(nullable = false)
    private int cid;

    /** 课程成绩 */
    @Column(nullable = false)
    private int score;

}
