package com.msb.internalcommon.constant;


import lombok.Getter;

@Getter
public enum CommonStatusEnum {
    SUCCESS(1,"success"),
    FAIL(0,"fail"),
    /**
     * 验证码不正确1000-1099
     */
    VERIFICATION_CODE_ERROR(1099,"验证码不正确"),
    /**
     * Token错误1100-1199
     */
    TOKEN_ERROR(1199,"token 错误"),
    /**
     * 用户提示1200-1299
     */
    USER_NOT_EXIST(1299,"当前用户不存在"),
    /**
     * 计价规则提示1300-1399
     */
    PRICE_RULE_EMPTY(1399,"计价规则不存在"),
    /**
     * 地图接口请求提示1400-1499
     */
    MAP_DISTRICT_ERROR(1400,"请求地图错误");
    @Getter
    private int code;
    @Getter
    private String value;
    CommonStatusEnum(int code,String value){
        this.code=code;
        this.value=value;
    }

}
