package com.ling.ems_vue.controller;

import com.ling.ems_vue.entity.Emp;
import com.ling.ems_vue.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("emp")
public class EmpController {


    @Autowired
    private EmpService empService;

    //保存员工信息的方法
    @PostMapping("save")
    public Map<String,Object> save(Emp emp, MultipartFile photo){
        Map<String,>
    }

    //获取员工列表的方法
    @GetMapping("findAll")
    public List<Emp> findAll(){
        return empService.findAll();
    }
}
