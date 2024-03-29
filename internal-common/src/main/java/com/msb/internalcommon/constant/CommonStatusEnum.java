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
    TOKEN_ERROR(1100,"token 格式错误"),
    TOKEN_NOT_EXIST(1101,"token 不存在"),
    TOKEN_NOT_RIGHT(1102,"token 不正确"),
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
    MAP_DISTRICT_ERROR(1400,"请求地图错误"),
    /**
     * 数据库操作提示1500-1599
     */
    INSERT_DB_FAILED(1500,"insert失败"),
    SELECT_DB_FAILED(1520,"select失败"),
    SELECT_NO_FOUND(1521,"未查询到结果"),
    UPDATE_DB_FAILED(1540,"update失败"),
    DELETE_DB_FAILED(1560,"delete失败"),
    /**
     * 地图接口请求提示1600-1699
     */
    DRIVER_CAR_BIND_NOT_EXIST(1600,"绑定关系不存在"),
    DRIVER__NOT_EXIST(1601,"司机不存在");
    @Getter
    private int code;
    @Getter
    private String value;
    CommonStatusEnum(int code,String value){
        this.code=code;
        this.value=value;
    }

}
