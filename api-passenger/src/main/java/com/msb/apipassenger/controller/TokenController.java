package com.msb.apipassenger.controller;

import com.msb.apipassenger.service.TokenService;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TokenController {
    @Autowired
    private TokenService tokenService;
    @ResponseBody
    @PostMapping("/refresh-token")
    public ResponseResult refreshToken(@RequestBody TokenResponse originalToken){
        String refreshToken=originalToken.getRefreshToken();
        System.out.println(refreshToken);
        return tokenService.refreshToken(refreshToken);
    }
}
