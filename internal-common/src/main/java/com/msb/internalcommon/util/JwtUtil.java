package com.msb.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.msb.internalcommon.dto.TokenResult;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    //盐
    private static final String SIGN="A#nd007.";
    private static final String JWT_KEY_PHONE="phone.";
    private static final String JWT_KEY_IDENTITY="identity.";
    private static final String JWT_KEY_TOKENTYPE="acessToken.";
    public static final String TIMESTAMP="2022";
    //生成token
    public static String generateToken(Map<String,String> map){
        JWTCreator.Builder builder=JWT.create();
        map.forEach(
                    (k,v)->{
                        builder.withClaim(k,v);
                    }
                );
        //生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));
        return sign;
    }
    //生成token,带过期时间参数
    public static String generateToken(Map<String,String> map,Date expireDate){
        //token过期时间,过期就解析不出来了
        JWTCreator.Builder builder=JWT.create();
        map.forEach(
                (k,v)->{
                    builder.withClaim(k,v);
                }
        );
        //整合过期时间
        builder.withExpiresAt(expireDate);
        //生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));
        return sign;
    }
    //生成token,重载方法
    public static String generateToken(String phone,String identity,String tokenType){
        Map<String,String> map=new HashMap<>();
        map.put(JWT_KEY_PHONE,phone);
        map.put(JWT_KEY_IDENTITY,identity);
        map.put(JWT_KEY_TOKENTYPE,tokenType);
        map.put(TIMESTAMP,Calendar.getInstance().getTime().toString());//加入时间，token就会不一样
        String sign = generateToken(map);
        return sign;
    }
    //解码
    public static TokenResult parseToken(String token) throws Exception {
        DecodedJWT verify=JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        Claim claimPhone = verify.getClaim(JWT_KEY_PHONE);//每一个键值对，就是一个claim
        Claim claimIdentity = verify.getClaim(JWT_KEY_IDENTITY);//每一个键值对，就是一个claim
        Claim claimTokenType=verify.getClaim(JWT_KEY_TOKENTYPE);//
        if (claimPhone==null || claimIdentity==null || claimTokenType==null){
            throw new Exception("Token is not correct format ! missing field !");
        }
        return new TokenResult()
                .setIdentity(claimIdentity.asString())
                .setPhone(claimPhone.asString())
                .setTokenType(claimTokenType.asString());
    }
    //利用parseToken解析token

    public static TokenResult checkToken(String token){
        TokenResult tokenResult=null;
        try {
            tokenResult=parseToken(token);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  tokenResult;
    }
    //测试用例
    public static void main(String[] args) throws Exception {
        Map<String,String> map=new HashMap<>();
        map.put(JWT_KEY_PHONE,"18710952920");
        map.put(JWT_KEY_IDENTITY,"1001");

        String s=generateToken("18710952920","1002","accessToken");
        System.out.println(s);
        System.out.println("解析结果："+parseToken(s));

        String s2=generateToken(map);
        System.out.println(s2);
        System.out.println("解析结果："+parseToken(s2));
    }
}
