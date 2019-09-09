package com.test.mapper;

import com.test.bean.Nation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface NationMapper {
    //查询所有
    @Select("select * from sys_nation")
    List<Nation> queryAll();

    //修改nation表的count
    @Update("update sys_nation set count=count+#{count} where id=#{id}")
    int updateCount(Nation nation);
}
