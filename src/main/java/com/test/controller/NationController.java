package com.test.controller;

import com.test.bean.Nation;
import com.test.service.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nation")
public class NationController {

    @Autowired
    private NationService nationService;

    @GetMapping("/")
    public List<Nation> getNationList(){
        return nationService.getNationList();
    }
}
