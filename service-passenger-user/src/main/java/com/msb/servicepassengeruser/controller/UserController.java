package com.msb.servicepassengeruser.controller;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.VerificationCodeDTO;
import com.msb.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("查询手机号用户："+passengerPhone);
        return userService.loginOrRegister(passengerPhone);
    }
    @GetMapping("/user/{phone}")
    public ResponseResult getUser(@PathVariable("phone") String phoneNumber){

        System.out.println("phoneNumber = " + phoneNumber);
        return  userService.getUserByPhone(phoneNumber);
    }
}
