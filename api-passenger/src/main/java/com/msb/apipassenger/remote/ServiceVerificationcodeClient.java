package com.msb.apipassenger.remote;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Service
@FeignClient("service-verificationcode")
public interface ServiceVerificationcodeClient {
//    @RequestMapping(method = RequestMethod.GET,value = "/numberCode/6")
    @GetMapping("/numberCode/{size}")
    ResponseResult<NumberCodeResponse>  getNumberCode(@PathVariable int size);
}
