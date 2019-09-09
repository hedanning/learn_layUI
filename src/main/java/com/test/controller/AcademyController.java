package com.test.controller;

import com.test.bean.Academy;
import com.test.service.AcademyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/academy")
public class AcademyController {
    @Autowired
    AcademyService academyService;

    @GetMapping("/")
    public List<Academy> getAcademyList(){
        return academyService.getAcademyList();
    }
}
