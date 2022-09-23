package com.msb.apidriver.service;

import com.msb.internalcommon.constant.CommonStatusEnum;
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
    public ResponseResult<TokenResponse> refreshTwoToken(String refreshTokenFromWeb){
        TokenResult tokenResult = JwtUtil.checkToken(refreshTokenFromWeb);
        if(tokenResult==null){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        //如果前端传来的refresh token可解析，生成key去查询refresh token
        String identity = tokenResult.getIdentity();
        String phone = tokenResult.getPhone();
        String keyAccess = RedisPrefixUtil.generateTokenKey(phone, identity, TokenTypeConstant.ACCESS);
        String keyRefresh = RedisPrefixUtil.generateTokenKey(phone, identity, TokenTypeConstant.REFRESH);
        String refreshTokenInRedis = stringRedisTemplate.opsForValue().get(keyRefresh);
        if(StringUtils.isBlank(refreshTokenInRedis)){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_NOT_EXIST.getCode(),CommonStatusEnum.TOKEN_NOT_EXIST.getValue());
        }
        else if(!refreshTokenInRedis.equals(refreshTokenFromWeb)){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_NOT_RIGHT.getCode(),CommonStatusEnum.TOKEN_NOT_RIGHT.getValue());
        }
        System.out.println("refresh token valid, refreshing tokens for you... ...");
        //到此，refreshTokenFromWeb验证成功，刷新双token
        String tokenAccess = JwtUtil.generateToken(phone, identity, TokenTypeConstant.ACCESS);
        String tokenRefresh = JwtUtil.generateToken(phone, identity, TokenTypeConstant.REFRESH);
        stringRedisTemplate.opsForValue().set(keyAccess,tokenAccess,30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(keyRefresh,tokenRefresh,31, TimeUnit.DAYS);

        return ResponseResult.success(new TokenResponse(tokenAccess,tokenRefresh));
    }

}
