package com.test.service;

import com.test.bean.Employee;
import com.test.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper empMapper;

    //条件查询
    public List<Employee> getEmployeeListByParams(Map<String, Object> params){
        return empMapper.queryEmployeeListByParams(params);
    }

    //条件查询总数
    public int getCountByParams(Map<String, Object> params){
        return empMapper.queryCountByParams(params);
    }

    //获取总数
    public int getCount(){
        return empMapper.getCount();
    }

    //分页查询
    public List<Employee> getEmployeeByPage(int currentIndex,int pageSize){
        return empMapper.queryEmployeeByPage(currentIndex,pageSize);
    }

    //查询所有的数据
    public List<Employee> getAll(){
        return empMapper.queryAll();
    }

    //新增employee
    public int addEmployee(Employee employee){
        return empMapper.addEmployee(employee);
    }

    //修改
    public int updateEmployee(Employee employee){
        return empMapper.updateEmployee(employee);
    }

    //根据id查询employee
    public Employee getEmployeeById(String id){
        return empMapper.queryEmployeeById(id);
    }

    //删除
    public int deleteEmployeeById(String[] ids){
        return empMapper.deleteEmploy(ids);
    }

}
