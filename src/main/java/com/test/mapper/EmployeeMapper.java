package com.test.mapper;

import com.test.bean.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmployeeMapper {
    //动态拼接查询语句
    @SelectProvider(type = LearnSqlBuilder.class, method = "queryLearnResouceByParams")
    List<Employee> queryEmployeeListByParams(Map<String, Object> params);

    //动态拼接查询总数
    @SelectProvider(type = LearnSqlBuilder.class, method = "queryCountByConParams")
    int queryCountByParams(Map<String, Object> params);

    //查询总数
    @Select("select count(*) from employee")
    int getCount();

    //分页查询语句
    @Select("select * from employee limit #{currentIndex} , #{pageSize}")
    List<Employee> queryEmployeeByPage(@Param("currentIndex")int currentIndex, @Param("pageSize")int pageSize);

    //查询所有
    @Select("select * from employee")
    List<Employee> queryAll();

    //新增
    @Insert("insert into employee(id,name,gender,salary) values(#{id},#{name},#{gender},#{salary})")
    int addEmployee(Employee employee);

    //修改
    @Update("update employee set name=#{name},gender=#{gender},salary=#{salary} where id=#{id}")
    int updateEmployee(Employee employee);

    //根据id查询employee
    @Select("select * from employee where id=#{id}")
    public Employee queryEmployeeById(String id);

    //删除
    @DeleteProvider(type = LearnSqlBuilder.class, method = "deleteEmployeeById")
    int deleteEmploy(@Param("ids") String[] ids);

    class LearnSqlBuilder {
        //动态构造查询语句
        public String queryLearnResouceByParams(final Map<String, Object> params) {
            StringBuffer sql =new StringBuffer();
            sql.append("select * from employee where 1=1");
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
            sql.append("select count(*) from employee where 1=1");
            if(!StringUtils.isEmpty((String)params.get("id"))){
                sql.append(" and id like '%").append((String)params.get("id")).append("%'");
            }
            if(!StringUtils.isEmpty((String)params.get("name"))){
                sql.append(" and name like '%").append((String)params.get("name")).append("%'");
            }
            if(!StringUtils.isEmpty((String)params.get("gender"))){
                sql.append(" and gender =").append((String)params.get("gender"));
            }
            if(!StringUtils.isEmpty((String)params.get("salary"))){
                sql.append(" and salary = ").append((String)params.get("salary"));
            }
            System.out.println("查询sql=="+sql.toString());
            return sql.toString();
        }

        public String deleteEmployeeById(@Param("ids") final String[] ids){
            StringBuffer sql = new StringBuffer();
            sql.append("delete from employee where id in (");
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
