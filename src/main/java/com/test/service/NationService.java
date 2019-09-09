package com.test.service;

import com.test.bean.Nation;
import com.test.mapper.NationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationService {
    @Autowired
    private NationMapper nationMapper;

    public List<Nation> getNationList(){
        return nationMapper.queryAll();
    }

    public int updateCount(Nation nation){
        return nationMapper.updateCount(nation);
    }

}
