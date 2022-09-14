package com.msb.apipassenger.service;

import com.msb.internalcommon.dto.PassengerUser;
import com.msb.internalcommon.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    public ResponseResult getUserByAccessToken(String accessToken){
        log.info("access token: "+accessToken)  ;
        PassengerUser passengerUser=new PassengerUser();
        passengerUser.setPassengerName("居科");
        passengerUser.setProfilePhoto("photo");
        return ResponseResult.success(passengerUser);

    }
}
