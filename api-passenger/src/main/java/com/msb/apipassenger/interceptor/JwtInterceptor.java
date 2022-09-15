package com.msb.apipassenger.interceptor;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.msb.internalcommon.constant.TokenTypeConstant;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.dto.TokenResult;
import com.msb.internalcommon.util.JwtUtil;
import com.msb.internalcommon.util.RedisPrefixUtil;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.omg.PortableInterceptor.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        TokenResult tokenResult=null;
        log.info("Interceptor：request detected.");
        //查看header中是否有authorization这个参数
        String token = request.getHeader("authorization");
        if (token==null){
            log.info("header中未找到authorization参数");
            response.getWriter().write(JSONObject.fromObject(ResponseResult.fail("No authorization found in httprequest Header !")).toString());
            return false;
        }
        //解析token，是否可解析
        tokenResult = JwtUtil.checkToken(token);
        if (tokenResult==null){
            log.info("令牌格式错误，解析失败！");
            response.getWriter().write(JSONObject.fromObject(ResponseResult.fail("token format error, fail to parse！")).toString());
            return false;
        }
        //解析有效，就取出来比较
        String identity = tokenResult.getIdentity();
        String phone = tokenResult.getPhone();
        String tokenKey = RedisPrefixUtil.generateTokenKey(phone, identity, TokenTypeConstant.ACCESS);
        String tokenRedis = stringRedisTemplate.opsForValue().get(tokenKey);
        if (!StringUtils.isBlank(tokenRedis) || token.trim().equals(tokenRedis.trim())){
            log.info("令牌有效！");
            return true;
        }
        else {
            log.info("令牌无效！");
            response.getWriter().write(JSONObject.fromObject(ResponseResult.fail("token not valid！")).toString());
            return false;
        }

    }
}
