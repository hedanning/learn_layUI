package com.test.mapper;

import com.test.bean.Academy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AcademyMapper {
    @Select("select * from sys_academy")
    public List<Academy> getAcademyList();

    //修改nation表的count
    @Update("update sys_academy set count=count+#{count} where id=#{id}")
    int updateCount(Academy academy);
}
