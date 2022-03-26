package com.chess.manage.web.enums;

/**
 * @Description : 接口返回公共码
 * @Author : wukong
 * @Date: 2021/11/7 12:30
 */
public enum ResultCodeEnum {
    SUCCESS(200, "成功"),
    FAIL(0, "失败"),
    ;

    int code;
    String message;

    ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}