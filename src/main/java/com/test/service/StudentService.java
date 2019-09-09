package com.test.service;

import com.test.bean.Student;
import com.test.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    //条件查询
    public List<Student> getStudentListByParams(Map<String, Object> params){
        return studentMapper.queryStudentListByParams(params);
    }

    //条件查询总数
    public int getCountByParams(Map<String, Object> params){
        return studentMapper.queryCountByParams(params);
    }

    //获取总数
    public int getCount(){
        return studentMapper.getCount();
    }

    //分页查询
    public List<Student> getStudentByPage(int currentIndex, int pageSize){
        return studentMapper.queryStudentByPage(currentIndex,pageSize);
    }

    //查询所有的数据
    public List<Student> getAll(){
        return studentMapper.queryAll();
    }

    //新增student
    public int addStudent(Student student){
        return studentMapper.addStudent(student);
    }

    //修改
    public int updateStudent(Student student){
        return studentMapper.updateStudent(student);
    }

    //根据id查询student
    public Student getStudentById(String id){
        return studentMapper.queryStudentById(id);
    }

    //删除
    public int deleteStudentById(String[] ids){
        return studentMapper.deleteStudent(ids);
    }

}
