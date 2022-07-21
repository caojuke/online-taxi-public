package com.msb.apipassenger.controller;


import com.msb.apipassenger.service.VerificationCodeService;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.VerificationCodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VerificationCodeController {
    @Autowired
    private VerificationCodeService verificationCodeService;
    @GetMapping("/verification-code")
    @ResponseBody
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone=verificationCodeDTO.getPassengerPhone();
        System.out.println("接收到的手机号： "+passengerPhone);
        return verificationCodeService.generateCode(passengerPhone);
    }
    @PostMapping("/verification-code-chek")
    @ResponseBody
    public ResponseResult checkVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String verificationCode = verificationCodeDTO.getVerificationCode();
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("verificationCode = " + verificationCode);
        System.out.println("passengerPhone = " + passengerPhone);
        return  verificationCodeService.checkCode(passengerPhone,verificationCode);
    }
}
