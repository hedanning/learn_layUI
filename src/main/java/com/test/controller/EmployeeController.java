package com.test.controller;

import com.test.bean.Employee;
import com.test.service.EmployeeService;
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
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService empService;

    @ApiOperation(value="获取employee列表",notes = "")
    @GetMapping("/")
    public @ResponseBody String getList(@RequestParam Map<String, Object> params){
        int page = Integer.valueOf((String)params.get("page"));
        int limit = Integer.valueOf((String)params.get("limit"));
        //int count = empService.getCount();
        /*int page_temp = page;
        int limit_temp = limit;
        if (count < page * limit) {
            limit = count - (page - 1) * limit;
        }
            page = (page_temp - 1) * limit_temp;


        Map<String, Object> params = new HashMap<>();
        List<Employee> list = empService.getEmployeeByPage(page,limit);*/

        /*
        * 以数组进行分页
        * */
        //List<Employee> list = empService.getAll();
        int count = empService.getCountByParams(params);
        List<Employee> list = empService.getEmployeeListByParams(params);
        //从第几条数据开始
        int firstIndex = (page - 1) * limit;
        //到第几条数据结束
        int lastIndex = page * limit;
        //如果lastIndex>count
        if(lastIndex>count){
            lastIndex = count;
        }
        list = list.subList(firstIndex, lastIndex); //直接在list中截取

        String jso = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+JSONArray.toJSONString(list)+"}";

        return jso;
        //return  JSONArray.toJSONString(empService.getEmployeeList(params)).toString();
    }

    @PostMapping("/")
    public JSONObject addEmployee(@ModelAttribute Employee employee){
        int i = empService.addEmployee(employee);
        JSONObject data = new JSONObject();
        data.put("success","false");
        if(i==1){
            data.put("success","true");
        }
        return data;
    }

    @PutMapping("/")
    public JSONObject update(@RequestBody Employee employee){
        int i = empService.updateEmployee(employee);
        JSONObject data = new JSONObject();
        data.put("success","false");
        if(i==1){
            data.put("success","true");
        }
        return data;
    }

    @DeleteMapping("/{ids}")
    public JSONObject delete(@PathVariable String[] ids){
        int i = empService.deleteEmployeeById(ids);
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
