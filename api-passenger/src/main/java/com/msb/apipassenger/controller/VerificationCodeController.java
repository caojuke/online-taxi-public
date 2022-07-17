package com.msb.apipassenger.controller;

import com.msb.apipassenger.request.VerificationCodeDTO;
import com.msb.apipassenger.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VerificationCodeController {
    @Autowired
    private VerificationCodeService verificationCodeService;
    @GetMapping("/verification-code")
    @ResponseBody
    public String verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        System.out.println("接收到的手机号： "+verificationCodeDTO.getPassengerPhone());
        return verificationCodeService.generateCode();
    }
}
