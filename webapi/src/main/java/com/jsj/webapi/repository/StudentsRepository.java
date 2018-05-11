package com.jsj.webapi.repository;

import com.jsj.common.repository.BaseRepository;
import com.jsj.webapi.dataobject.Students;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jinshouji on 2018/4/25.
 */
public interface StudentsRepository extends BaseRepository<Students, Integer>
{
    Page<Students> findBySnameLikeAndSnoLike(String sname,String sno, Pageable pageable);

    Students getStudentBySnameAndSno(String sname,String sno);
}
