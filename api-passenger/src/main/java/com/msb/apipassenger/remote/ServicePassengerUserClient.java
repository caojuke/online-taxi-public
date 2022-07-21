package com.msb.apipassenger.remote;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {
    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO);
}
