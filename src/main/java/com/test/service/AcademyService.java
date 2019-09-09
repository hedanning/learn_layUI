package com.test.service;

import com.test.bean.Academy;
import com.test.mapper.AcademyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademyService {
    @Autowired
    AcademyMapper academyMapper;
    public List<Academy> getAcademyList(){
        return academyMapper.getAcademyList();
    }

    public int updateCount(Academy academy){
        return academyMapper.updateCount(academy);
    }
}
