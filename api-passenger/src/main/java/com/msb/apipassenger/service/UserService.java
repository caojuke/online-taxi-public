package com.msb.apipassenger.service;

import com.msb.apipassenger.remote.ServicePassengerUserClient;
import com.msb.internalcommon.dto.PassengerUser;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.dto.TokenResult;
import com.msb.internalcommon.request.VerificationCodeDTO;
import com.msb.internalcommon.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    ServicePassengerUserClient servicePassengerUserClient;
    public ResponseResult getUserByAccessToken(String accessToken){
        log.info("access token: "+accessToken)  ;
        //先解析令牌中的手机号
        TokenResult tokenResult = JwtUtil.checkToken(accessToken);
        String phone=tokenResult.getPhone();

        //调用feign接口，用手机号获取用户

        return servicePassengerUserClient.getUserByPhone(phone);

    }
}
