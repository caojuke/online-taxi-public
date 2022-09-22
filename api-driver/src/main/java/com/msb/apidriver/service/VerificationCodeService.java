package com.msb.apidriver.service;

import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService {

    public ResponseResult checkAndSendVerificationCode(String driverPhone){
        //查询手机号是否存在，去service-driver-user中去查

        //获取验证码

        //调用第三方发送验证码

        //存入redis

        return ResponseResult.success(driverPhone);
    }
}
