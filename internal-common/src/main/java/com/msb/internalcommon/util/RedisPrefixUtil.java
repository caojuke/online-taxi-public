package com.msb.internalcommon.util;

public class RedisPrefixUtil {
    //a prefix for redis key
    private static String verificationCodePrefix="passenger-verification-code-";
    public static String tokenPrefix="token-";
    public static String generateTokenKey(String passengerPhone, String passengerIdentity) {
        return tokenPrefix+passengerPhone+"-"+passengerIdentity;
    }

    public static  String generateKeyByPhone(String passengerPhone){
        return verificationCodePrefix+passengerPhone;
    }
}