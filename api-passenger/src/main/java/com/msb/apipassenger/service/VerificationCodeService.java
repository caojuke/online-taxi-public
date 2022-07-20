package com.msb.apipassenger.service;

import com.msb.apipassenger.remote.ServiceVerificationcodeClient;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService {
    @Autowired
    private ServiceVerificationcodeClient serviceVerificationcodeClient;
    public String generateCode(){
        //调用验证码服务
        System.out.println("调用验证码服务中，获取验证码...");
        ResponseResult<NumberCodeResponse> numberCode = serviceVerificationcodeClient.getNumberCode(6);
        int numberCodeData=numberCode.getData().getNumberCode();
        System.out.println("numberCodeData = " + numberCodeData);
        //存入redis
        System.out.println("存入redis...");
        //返回值
        /*JSONObject result=new JSONObject();
        result.put("code","1");
        result.put("message","success");*/
        return numberCode.toString();
    }
}
