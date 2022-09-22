package com.msb.apidriver.controller;

import com.msb.apidriver.service.VerificationCodeService;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.VerificationCodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationCodeController {
    @Autowired
    private VerificationCodeService verificationCodeService;
    @GetMapping("/verification-code")
    public ResponseResult getVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        //service实现验证和获取验证码

        return verificationCodeService.checkAndSendVerificationCode(verificationCodeDTO.getDriverPhone());
    }
}
