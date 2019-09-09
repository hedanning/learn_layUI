package com.test.controller;

import com.test.bean.Academy;
import com.test.bean.Student;
import com.test.bean.Nation;
import com.test.service.AcademyService;
import com.test.service.StudentService;
import com.test.service.NationService;
import io.swagger.annotations.ApiOperation;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

//@Api(value = "employeeController",description = "用户相关操作",tags = {"用户"})
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService empService;

    @Autowired
    private NationService nationService;

    @Autowired
    private AcademyService academyService;

    @ApiOperation(value="获取student列表",notes = "")
    @GetMapping("/")
    public @ResponseBody String getList(@RequestParam Map<String, Object> params){
        //当前页
        int page = Integer.valueOf((String)params.get("page"));
        //每页显示的条目
        int limit = Integer.valueOf((String)params.get("limit"));
        /*//总数量
        int count = empService.getCount();
        int page_temp = page;
        int limit_temp = limit;
        //如果剩余的不够当前页显示的，就显示剩余的条目
        if (count < page * limit) {
            limit = count - (page - 1) * limit;
        }
        page = (page_temp - 1) * limit_temp;


        //Map<String, Object> params = new HashMap<>();
        List<Student> list = empService.getEmployeeByPage(page,limit);*/

        /*
        * 以数组进行分页
        * */
        //List<Student> list = empService.getAll();
        int count = empService.getCountByParams(params);
        List<Student> list = empService.getStudentListByParams(params);
        //从第几条数据开始
        int firstIndex = (page - 1) * limit;
        //到第几条数据结束
        int lastIndex = page * limit;
        //如果lastIndex>count
        if(lastIndex>count){
            lastIndex = count;
        }
        list = list.subList(firstIndex, lastIndex); //直接在list中截取*/

        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+JSONArray.toJSONString(list)+"}";

        return jso;
    }

    @PostMapping("/")
    public JSONObject addStudent(@ModelAttribute Student student){
        int i = empService.addStudent(student);
        //同步修改nation表中的count数据
        Nation nation = new Nation();
        nation.setId(student.getNationId());
        nation.setCount(1);
        int j =nationService.updateCount(nation);
        //同步修改academy表中的count数据
        Academy academy = new Academy(student.getAcademyId(),1);
        academyService.updateCount(academy);
        JSONObject data = new JSONObject();
        data.put("success","false");
        if(i==1&&j==1){
            data.put("success","true");
        }
        return data;
    }

    @PutMapping("/")
    public JSONObject update(@RequestBody Student student){
        Student temp_student = empService.getStudentById(student.getId());
        int i = empService.updateStudent(student);
        //同步修改nation表中的count数据
        Nation nationOld = new Nation(temp_student.getNationId(),-1);
        Nation nationNew = new Nation(student.getNationId(),1);
        if(student.getNationId()!= temp_student.getNationId()){
            nationService.updateCount(nationOld);
            nationService.updateCount(nationNew);
        }
        //同步修改academy表中的count数据
        Academy academyOld = new Academy(temp_student.getAcademyId(),-1);
        Academy academyNew = new Academy(student.getAcademyId(),1);
        if(student.getAcademyId()!= temp_student.getAcademyId()){
            academyService.updateCount(academyOld);
            academyService.updateCount(academyNew);
        }
        JSONObject data = new JSONObject();
        data.put("success","false");
        if(i==1){
            data.put("success","true");
        }
        return data;
    }

    @DeleteMapping("/{ids}")
    public JSONObject delete(@PathVariable String[] ids){
        //同步修改nation中的count
        Nation nation =null;
        Student student =null;
        for (String id:ids) {
            student = empService.getStudentById(id);
            nation = new Nation(student.getNationId(),-1);
            nationService.updateCount(nation);
        }
        //同步修改nation中的count
        Academy academy =null;
        for (String id:ids) {
            student = empService.getStudentById(id);
            academy = new Academy(student.getAcademyId(),-1);
            academyService.updateCount(academy);
        }
        int i = empService.deleteStudentById(ids);
        JSONObject data = new JSONObject();
        data.put("success","false");
        if(i>0){
            data.put("success","true");
            data.put("count",i);
        }
        System.out.println(data);
        return data;
    }
    @GetMapping("/index")
    public ModelAndView getIndex(){
        ModelAndView modelAndView=new ModelAndView();

        modelAndView.setViewName("index_page");
        return modelAndView;
    }



    @GetMapping("/index1")
    public ModelAndView getIndex1(){
        ModelAndView modelAndView=new ModelAndView();

        modelAndView.setViewName("index");
        return modelAndView;
    }

}
