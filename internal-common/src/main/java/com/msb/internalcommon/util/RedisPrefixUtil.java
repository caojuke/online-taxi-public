package com.msb.internalcommon.util;

public class RedisPrefixUtil {
    //a prefix for redis key
    private static String verificationCodePrefix="verification-code-";
    public static String tokenPrefix="token-";
    public static String generateTokenKey(String phone, String identity,String tokenType) {
        return tokenPrefix+phone+"-"+identity+"-"+tokenType;
    }

    public static  String generateKeyByPhone(String phone,String identity){
        return verificationCodePrefix+"-"+phone+"-"+identity;
    }
}
