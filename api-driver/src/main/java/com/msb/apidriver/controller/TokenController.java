package com.msb.apidriver.controller;

import com.msb.apidriver.service.TokenService;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.dto.TokenResult;
import com.msb.internalcommon.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {
    @Autowired
    private TokenService tokenService;
    @PostMapping("/refresh-token")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse){
        System.out.println(tokenResponse.getRefreshToken());
        return tokenService.refreshTwoToken(tokenResponse.getRefreshToken());
    }
}
