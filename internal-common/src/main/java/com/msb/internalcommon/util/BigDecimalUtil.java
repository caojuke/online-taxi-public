package com.msb.internalcommon.util;

import java.math.BigDecimal;

public class BigDecimalUtil {
    public static double add(double v1,double v2){
        if(v1<0 || v2 <0){
            throw new IllegalArgumentException("参数不能为小于零的值！");
        }
        BigDecimal b1=new BigDecimal(v1);
        BigDecimal b2=new BigDecimal(v2);
        BigDecimal res = b1.add(b2);
        res=res.setScale(2, BigDecimal.ROUND_HALF_UP);
        return res.doubleValue();
    }
    public static double subtract(double v1,double v2){
        if(v1<0 || v2 <0){
            throw new IllegalArgumentException("参数不能为小于零的值！");
        }
        BigDecimal b1=new BigDecimal(v1);
        BigDecimal b2=new BigDecimal(v2);
        BigDecimal res = b1.subtract(b2);
        res=res.setScale(2, BigDecimal.ROUND_HALF_UP);
        return res.doubleValue();
    }
    public static double multiply(double v1,double v2){
        if(v1<0 || v2 <0){
            throw new IllegalArgumentException("参数不能为小于零的值！");
        }
        BigDecimal b1=new BigDecimal(v1);
        BigDecimal b2=new BigDecimal(v2);
        BigDecimal res = b1.multiply(b2);
        res=res.setScale(2, BigDecimal.ROUND_HALF_UP);
        return res.doubleValue();
    }
    public static double divide(double v1,double v2){
        if(v1<0 || v2 <0){
            throw new IllegalArgumentException("参数不能为小于零的值！");
        }
        BigDecimal b1=new BigDecimal(v1);
        BigDecimal b2=new BigDecimal(v2);
        BigDecimal res = b1.divide(b2);
        res=res.setScale(2, BigDecimal.ROUND_HALF_UP);
        return res.doubleValue();
    }

    public static void main(String[] args) {
        System.out.println(add(-1.234,2.00107));
    }
}
