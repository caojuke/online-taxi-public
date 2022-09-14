package com.msb.apipassenger.service;

import com.msb.internalcommon.constant.TokenTypeConstant;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.dto.TokenResult;
import com.msb.internalcommon.response.TokenResponse;
import com.msb.internalcommon.util.JwtUtil;
import com.msb.internalcommon.util.RedisPrefixUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    public ResponseResult refreshToken(String originalToken){
        TokenResult originalTokenResult = JwtUtil.checkToken(originalToken);
        if (originalTokenResult==null){
            return ResponseResult.fail(1100,"token in http response failed to parse...");
        }
        String phone = originalTokenResult.getPhone();
        String identity = originalTokenResult.getIdentity();
        String tokenType = originalTokenResult.getTokenType();
        String refreshKey= RedisPrefixUtil.generateTokenKey(phone,identity,TokenTypeConstant.REFRESH);
        String accessKey= RedisPrefixUtil.generateTokenKey(phone,identity,TokenTypeConstant.ACCESS);
        //用key去redis中查询token
        String tokenRedis=stringRedisTemplate.opsForValue().get(refreshKey);
        if (StringUtils.isBlank(tokenRedis) || !originalToken.trim().equals(tokenRedis.trim())){
            return ResponseResult.fail(1100,"token is invalid");
        }
        //验证成功以后，生成双tokens
        String accessToken = JwtUtil.generateToken(phone, identity, TokenTypeConstant.ACCESS);
        String refreshToken = JwtUtil.generateToken(phone, identity, TokenTypeConstant.REFRESH);
        //存入redis
        stringRedisTemplate.opsForValue().set(accessKey,accessToken,15, TimeUnit.SECONDS);
        stringRedisTemplate.opsForValue().set(refreshKey,refreshToken,2, TimeUnit.MINUTES);
        //封装结果
        TokenResponse tokenResponse=new TokenResponse();
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setAccessToken(accessToken);

        return ResponseResult.success(tokenResponse);
    }
}
