package com.msb.apipassenger.interceptor;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.dto.TokenResult;
import com.msb.internalcommon.util.JwtUtil;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import net.sf.json.JSONObject;
import org.omg.PortableInterceptor.Interceptor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("request on your web detected...");
        String resultString="";
        boolean result=false;
        String authorization = request.getHeader("authorization");
        try {
            //解析token
            TokenResult tokenResult = JwtUtil.parseToken(authorization);
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
    //告知前端这个token错误！！
        if (!result){
            response.getWriter().write(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
        }

        return result;
    }
}
