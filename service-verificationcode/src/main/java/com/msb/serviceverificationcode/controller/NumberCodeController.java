package com.msb.serviceverificationcode.controller;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberCodeController {
    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable int size){
        System.out.println("size = " + size);
        //generate random number
        double randomDouble=(Math.random()*9+1)*Math.pow(10,size-1);
        int randomInt=(int)randomDouble;

        NumberCodeResponse response=new NumberCodeResponse();
        response.setNumberCode(randomInt);
        return ResponseResult.success(response);
    }
}
