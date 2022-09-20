package com.msb.servicemap.controller;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicemap.service.DicDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistrictController {
    @Autowired
    DicDistrictService dicDistrictService;
    @GetMapping("/dic-district")
    public ResponseResult test(){
       return dicDistrictService.initDicDistrict("中国");
    }


}
