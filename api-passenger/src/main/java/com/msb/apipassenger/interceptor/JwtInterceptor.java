package com.msb.apipassenger.interceptor;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.dto.TokenResult;
import com.msb.internalcommon.util.JwtUtil;
import com.msb.internalcommon.util.RedisPrefixUtil;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import net.sf.json.JSONObject;
import org.omg.PortableInterceptor.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        TokenResult tokenResult=null;
        System.out.println("request on your web detected...");
        String resultString="";
        boolean result=false;
        String token = request.getHeader("authorization");
        //解析token，是否存在
        try {
            tokenResult = JwtUtil.parseToken(token);
            result=true;
        }
        catch (TokenExpiredException e){
            resultString="token expired !";
            result=false;
        }
        catch (Exception e){
            resultString="token is invalid !";
            result=false;
        }
        //token存在，是否为空
        if (tokenResult==null){
            resultString="token is invalid !";
            result=false;
        }
        //不为空，就取出来比较
        String identity = tokenResult.getIdentity();
        String phone = tokenResult.getPhone();
        String tokenKey = RedisPrefixUtil.generateTokenKey(phone, identity);
        String tokenRedis = stringRedisTemplate.opsForValue().get(tokenKey);
        if (token.trim().equals(tokenRedis.trim())){
            System.out.println("令牌有效，允许访问... ...");
        }
        else {
            resultString="token is invalid !";
            result=false;
        }

        //告知前端这个token错误！！
        if (!result){
            response.getWriter().write(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
        }

        return result;
    }
}
