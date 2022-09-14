package com.msb.internalcommon.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public enum CommonStatusEnum {
    SUCCESS(1,"success"),
    FAIL(0,"fail"),
    /**
     * 验证码不正确1000-1099
     */
    VERIFICATION_CODE_ERROR(1099,"验证码不正确"),
    TOKEN_ERROR(1199,"token 错误"),
    USER_NOT_EXIST(1200,"当前用户不存在");
    @Getter
    private int code;
    @Getter
    private String value;
    CommonStatusEnum(int code,String value){
        this.code=code;
        this.value=value;
    }

}
