package com.msb.servicemap.controller;


import com.msb.internalcommon.dto.DicDistrict;
import com.msb.servicemap.mapper.DistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class TestController {

    @Autowired
    DistrictMapper districtMapper;
    @GetMapping("/testAdd/{code}/{name}")
    public String testAdd(@PathVariable String code,@PathVariable String name){
        try {
            int res = districtMapper.addDistrict(new DicDistrict(code, name, "100000", 1));
        }
        catch (Exception exception){
            return "fail to add district! Reason: "+ exception.getMessage();
        }
        return "New district "+ name+" added ok !";
    }

    @GetMapping("/testQuery/{code}")
    public String testQuery(@PathVariable String code){
        List<DicDistrict> res = districtMapper.selectByCode(code);
        return "query result: "+ res.toString();
    }
}
