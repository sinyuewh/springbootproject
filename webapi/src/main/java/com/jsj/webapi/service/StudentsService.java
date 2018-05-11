package com.jsj.webapi.service;

import com.jsj.common.bean.HttpResult;
import com.jsj.common.service.BaseService;
import com.jsj.common.utils.HttpResultUtil;
import com.jsj.common.utils.jpa.PageUtil;
import com.jsj.common.utils.jpa.SearchField;
import com.jsj.common.utils.jpa.SearchOperator;
import com.jsj.webapi.dataobject.Students;
import com.jsj.webapi.repository.StudentsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jinshouji on 2018/4/25.
 * @Author ： 金寿吉
 * @Remark ： 学生服务类
 */
@Service
@Slf4j
public class StudentsService extends BaseService<Students,Integer>
{
    //#region 全局变量
    //#endregion

    //#region entityManager 和 em

    //#endregion 等

    //#region 数据操作Dao 和其他的Service
    @Autowired
    private StudentsRepository studentsRepository;

    //#endregion

    //#region Controller 使用方法

    //查询方法1：直接使用dao中定义的方法，仅适合单表查询
    //@Transactional
    public Page getSearchResult1(String sname, String sno, Integer pageIndex, Integer pageSize)
    {
        PageRequest pageRequest = PageUtil.buildPageRequest(pageIndex, pageSize);
        sname="%"+sname+"%";
        sno="%"+sno+"%";

        return studentsRepository.findBySnameLikeAndSnoLike(sname,sno,pageRequest);
    }

    //查询方法2：直接使用dao中定义的扩展方法，仅适合单表查询
    //@Transactional
    public Page getSearchResult2(String sname, String sno, Integer pageIndex, Integer pageSize)
    {
        PageRequest pageRequest = PageUtil.buildPageRequest(pageIndex, pageSize);
        Map<String,Object> map1=new HashMap<String,Object>();
        sname="%"+sname+"%";
        sno="%"+sno+"%";
        map1.put("LIKE_sname",sname);
        map1.put("LIKE_sno",sno);
        return this.findAll(map1,pageRequest);
    }

    //查询方法3：通过getNativeSQLPage执行原生的sql语句
    //支持多表关联、别名、排序、统计等
    //@Transactional
    public Page getSearchResult(String sname, String sno, Integer pageIndex, Integer pageSize)
    {
       try {
           List<SearchField> condition = new ArrayList<SearchField>();
           condition.add(new SearchField("sname", sname));
          // condition.add(new SearchField("sno", "%" + sno + "%", SearchOperator.Contains));

           return this.getNativeSQLPage("select sname as 姓名,sno 学号 {from} where {where} order by sname",
                   "from students", pageIndex, pageSize, condition);
       }
       catch(Exception e)
       {
           log.error(e.getMessage());
       }
       return null;
    }

    @Transactional
    public int test1()
    {
       int count1=0;
       List<SearchField> condition=new ArrayList<SearchField>();
       condition.add(new SearchField("id",1,SearchOperator.BiggerAndEqual));

       Map<String,Object> map1=new HashMap<String,Object>();
       map1.put("Sname","jsj");
       map1.put("address","hust");


       Map<String,Object> s1= this.getFirstDataToMap("sname,sno","",condition);
       Page p1=this.getPageListMapData("*","",condition,-1,-1);

       return count1;
    }
    //#endregion

    //#region 私有方法
    //#endregion
}
