package com.msb.apipassenger.service;

import com.msb.internalcommon.dto.PassengerUser;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.dto.TokenResult;
import com.msb.internalcommon.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    public ResponseResult getUserByAccessToken(String accessToken){
        log.info("access token: "+accessToken)  ;
        TokenResult tokenResult = JwtUtil.checkToken(accessToken);
        String phone=tokenResult.getPhone();
        PassengerUser passengerUser=new PassengerUser();
        passengerUser.setPassengerName("居科");
        passengerUser.setProfilePhoto("photo");
        passengerUser.setPassengerPhone(phone);
        return ResponseResult.success(passengerUser);

    }
}
