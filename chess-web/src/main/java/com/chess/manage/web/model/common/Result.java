package com.chess.manage.web.model.common;


import com.chess.manage.web.enums.ResultCodeEnum;

/**
 * @Description : 接口结果返回封装
 * @Author : wukong
 * @Date: 2021/11/7 11:30
 */
public class Result {
    /**
     * 返回代码
     */
    private int code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 成功结果
     */
    transient
    public final static Result SUCCESS = new Result(ResultCodeEnum.SUCCESS);
    /**
     * 失败结果
     */
    transient
    public final static Result FAIL = new Result(ResultCodeEnum.FAIL);

    public Result() {
    }

    public Result(int code) {
        this.code = code;
    }

    public Result(int code, String message) {
        this(code);
        this.message = message;
    }

    public Result(ResultCodeEnum resultCodeEnum) {
        this(resultCodeEnum.getCode(), resultCodeEnum.getMessage());
    }

    public boolean successful(){
        return ResultCodeEnum.SUCCESS.getCode() == this.code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
