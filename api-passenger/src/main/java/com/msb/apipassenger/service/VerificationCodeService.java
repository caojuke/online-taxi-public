package com.msb.apipassenger.service;

import com.msb.apipassenger.remote.ServicePassengerUserClient;
import com.msb.apipassenger.remote.ServiceVerificationcodeClient;
import com.msb.internalcommon.constant.CommonStatusEnum;
import com.msb.internalcommon.constant.IdentityConstant;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.VerificationCodeDTO;
import com.msb.internalcommon.response.NumberCodeResponse;
import com.msb.internalcommon.response.TokenResponse;
import com.msb.internalcommon.util.JwtUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeService {
    @Value("${spring.redis.host}")
    private String redisServer;
    @Autowired
    private ServiceVerificationcodeClient serviceVerificationcodeClient;
    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;
    //a prefix for redis key
    private String verificationCodePrefix="passenger-verification-code-";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;//如果key是字符串，就用这个工具；如果不是，用RedisTemplate!!!
    public ResponseResult generateCode(String passengerPhone){

        //调用验证码服务
        System.out.println("调用验证码服务中，获取验证码...");
        ResponseResult<NumberCodeResponse> numberCode = serviceVerificationcodeClient.getNumberCode(6);
        int numberCodeData=numberCode.getData().getNumberCode();
        System.out.println("得到验证码： " + numberCodeData);

        //存入redis,key为前缀加手机号，value为验证码
        System.out.println("存入redis服务器: "+redisServer+"，有效时间2分钟...");
        String key=generateKeyByPhone(passengerPhone);
        String value=String.valueOf(numberCodeData);
        stringRedisTemplate.opsForValue().set(key,value,2, TimeUnit.MINUTES);

        //通过第三方短信服务，给passengerPhone发送numberCodeData验证码,比如阿里，腾讯，华信短信服务
        System.out.println("发送验证码 "+numberCodeData+" 到客户手机："+passengerPhone);

        //返回值
        return ResponseResult.success(numberCodeData);
    }

    /**
     * 校验验证码
     * @param passengerPhone
     * @param verificationCode
     * @return
     */
    public ResponseResult checkCode(String passengerPhone,String verificationCode){
        //根据手机号，去redis读取验证码
        System.out.println("根据手机号，去redis读取验证码");
        String key=generateKeyByPhone(passengerPhone);
        String codeInRedis = stringRedisTemplate.opsForValue().get(key);
        System.out.println("codeInRedis = " + codeInRedis);
        System.out.println("code from client = "+verificationCode);
        //校验
        System.out.println(verificationCode.equals(codeInRedis)?"校验成功！":"校验失败！\n");
        if(!verificationCode.trim().equals(codeInRedis.trim())){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }

        VerificationCodeDTO verificationCodeDTO=new VerificationCodeDTO();
        verificationCodeDTO.setPassengerPhone(passengerPhone);
        ResponseResult responseResult = servicePassengerUserClient.loginOrRegister(verificationCodeDTO);
        //判断用户是否存在，并酌情处理
        System.out.println("颁发令牌");
        String token = JwtUtil.generateToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY);
        TokenResponse tokenResponse=new TokenResponse();
        tokenResponse.setToken(token);

        return ResponseResult.success(tokenResponse);
    }

    private String generateKeyByPhone(String passengerPhone){
        return verificationCodePrefix+passengerPhone;
    }
}
