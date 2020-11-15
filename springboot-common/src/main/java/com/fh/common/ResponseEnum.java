package com.fh.common;

public enum ResponseEnum {
    SUCCESS(200,"成功"),
    ERROR(1001,"失败"),
    LOING_ERROR(1002,"登录失败")
    ;
    private Integer code;
    private String message;

    ResponseEnum() {
    }

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
