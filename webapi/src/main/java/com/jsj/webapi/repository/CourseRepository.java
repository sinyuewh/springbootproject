package com.jsj.webapi.repository;

import com.jsj.webapi.dataobject.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jinshouji on 2018/4/25.
 */
public interface CourseRepository extends JpaRepository<Course, Integer>
{

}
