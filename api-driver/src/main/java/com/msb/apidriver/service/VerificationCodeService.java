package com.msb.apidriver.service;

import com.msb.apidriver.remote.ServiceDriverUserClient;
import com.msb.apidriver.remote.ServiceVerificationcodeClient;
import com.msb.internalcommon.constant.CommonStatusEnum;
import com.msb.internalcommon.constant.IdentityConstant;
import com.msb.internalcommon.constant.TokenTypeConstant;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.DriverExistResponse;
import com.msb.internalcommon.response.NumberCodeResponse;
import com.msb.internalcommon.response.TokenResponse;
import com.msb.internalcommon.util.JwtUtil;
import com.msb.internalcommon.util.RedisPrefixUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class VerificationCodeService {
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;
    @Autowired
    private ServiceVerificationcodeClient serviceVerificationcodeClient;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 在司机表中检查手机号是否存在，如存在，发验证码
     * @param driverPhone
     * @return
     */
    public ResponseResult<NumberCodeResponse> checkAndSendVerificationCode(String driverPhone){
        //查询手机号是否存在，去service-driver-user中去查
        ResponseResult<DriverExistResponse> result = serviceDriverUserClient.getUser(driverPhone);
        if(result.getData().getIsExist()!=1){
            log.info("手机号码"+driverPhone+"不存在，验证码未发送");
            return ResponseResult.fail(CommonStatusEnum.DRIVER__NOT_EXIST.getCode(),CommonStatusEnum.DRIVER__NOT_EXIST.getValue(),new NumberCodeResponse());
        }
        //获取验证码
        ResponseResult<NumberCodeResponse> numberCode = serviceVerificationcodeClient.getNumberCode(6);
        int verificationCode=numberCode.getData().getNumberCode();
        log.info("验证码："+verificationCode);
        //调用第三方发送验证码

        //存入redis
        String key= RedisPrefixUtil.generateKeyByPhone(driverPhone,IdentityConstant.DRIVER_IDENTITY);
        stringRedisTemplate.opsForValue().set(key,String.valueOf(verificationCode),3, TimeUnit.MINUTES);

        return ResponseResult.success(new NumberCodeResponse(verificationCode));
    }

    public ResponseResult<TokenResponse> checkCode(String driverPhone, String verificationCode) {
        //去redis中读取验证码
        String key= RedisPrefixUtil.generateKeyByPhone(driverPhone,IdentityConstant.DRIVER_IDENTITY);
        String codeInRedis = stringRedisTemplate.opsForValue().get(key);
        //验证,颁发令牌
        if(verificationCode.equals(codeInRedis)){
            //双令牌存入redis
            String keyAccess=RedisPrefixUtil.generateTokenKey(driverPhone, IdentityConstant.DRIVER_IDENTITY, TokenTypeConstant.ACCESS);
            String keyRefresh=RedisPrefixUtil.generateTokenKey(driverPhone, IdentityConstant.DRIVER_IDENTITY, TokenTypeConstant.REFRESH);
            String accessToken= JwtUtil.generateToken(driverPhone,IdentityConstant.DRIVER_IDENTITY,TokenTypeConstant.ACCESS);
            String refreshToken= JwtUtil.generateToken(driverPhone,IdentityConstant.DRIVER_IDENTITY,TokenTypeConstant.REFRESH);
            stringRedisTemplate.opsForValue().set(keyAccess,accessToken,30,TimeUnit.DAYS);
            stringRedisTemplate.opsForValue().set(keyRefresh,refreshToken,30,TimeUnit.DAYS);
            //发给客户
            return ResponseResult.success(new TokenResponse(accessToken,refreshToken));
        }else {
            return ResponseResult.fail(new TokenResponse());
        }
    }
}
