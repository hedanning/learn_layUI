package com.test.mapper;

import com.test.bean.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    //动态拼接查询语句
    @SelectProvider(type = LearnSqlBuilder.class, method = "queryLearnResouceByParams")
    List<Student> queryStudentListByParams(Map<String, Object> params);

    //动态拼接查询总数
    @SelectProvider(type = LearnSqlBuilder.class, method = "queryCountByConParams")
    int queryCountByParams(Map<String, Object> params);

    //查询总数
    @Select("select count(*) from sys_student")
    int getCount();

    //分页查询语句
    @Select("select * from sys_student limit #{currentIndex} , #{pageSize}")
    List<Student> queryStudentByPage(@Param("currentIndex")int currentIndex, @Param("pageSize")int pageSize);

    //查询所有
    @Select("select * from sys_student")
    List<Student> queryAll();

    //新增
    @Insert("insert into sys_student(name,sex,schoolDate,identity,nationId,academyId) values(#{name},#{sex},#{schoolDate},#{identity},#{nationId},#{academyId})")
    int addStudent(Student student);

    //修改
    @Update("update sys_student set name=#{name},sex=#{sex},schoolDate=#{schoolDate},identity=#{identity},nationId=#{nationId},academyId=#{academyId},sfxx=#{sfxx},sfqj=#{sfqj} where id=#{id}")
    int updateStudent(Student student);

    //student
    @Select("select * from sys_student where id=#{id}")
    public Student queryStudentById(String id);

    //删除
    @DeleteProvider(type = LearnSqlBuilder.class, method = "deleteStudentById")
    int deleteStudent(@Param("ids") String[] ids);

    class LearnSqlBuilder {
        //动态构造查询语句
        public String queryLearnResouceByParams(final Map<String, Object> params) {
            StringBuffer sql =new StringBuffer();
            sql.append("select * from sys_student where 1=1");
            if(!StringUtils.isEmpty((String)params.get("id"))){
                sql.append(" and id like '%").append((String)params.get("id")).append("%'");
            }
            if(!StringUtils.isEmpty((String)params.get("name"))){
                sql.append(" and name like '%").append((String)params.get("name")).append("%'");
            }
            if(!StringUtils.isEmpty((String)params.get("gender"))){
                sql.append(" and gender = ").append((String)params.get("gender"));
            }
            if(!StringUtils.isEmpty((String)params.get("salary"))){
                sql.append(" and salary = ").append((String)params.get("salary"));
            }
            System.out.println("查询sql=="+sql.toString());
            return sql.toString();
        }
        public String queryCountByConParams(final Map<String, Object> params) {
            StringBuffer sql =new StringBuffer();
            sql.append("select count(*) from sys_student where 1=1");
            if(!StringUtils.isEmpty((String)params.get("id"))){
                sql.append(" and id like '%").append((String)params.get("id")).append("%'");
            }
            if(!StringUtils.isEmpty((String)params.get("name"))){
                sql.append(" and name like '%").append((String)params.get("name")).append("%'");
            }
            if(!StringUtils.isEmpty((String)params.get("sex"))){
                sql.append(" and sex =").append((String)params.get("sex"));
            }
            if(!StringUtils.isEmpty((String)params.get("salary"))){
                sql.append(" and salary = ").append((String)params.get("salary"));
            }
            System.out.println("查询sql=="+sql.toString());
            return sql.toString();
        }

        public String deleteStudentById(@Param("ids") final String[] ids){
            StringBuffer sql = new StringBuffer();
            sql.append("delete from sys_student where id in (");
            for (int i=0;i<ids.length;i++){
                if(i==ids.length-1){
                    sql.append(ids[i]);
                }else{

                    sql.append(ids[i]).append(",");
                }
            }
            sql.append(")");
            System.out.println("删除sql=="+sql.toString());
            return sql.toString();
        }

    }



}
