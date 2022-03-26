package com.chess.manage.web.model.common;


import com.chess.manage.web.enums.ResultCodeEnum;

/**
 * @Description : 接口结果返回封装
 * @Author : wukong
 * @Date: 2021/11/7 11:30
 */
public class DataResult<T> extends Result {
    /**
     * 数据
     */
    private T data;

    /**
     * 失败结果
     */
    transient
    public final static DataResult FAIL = new DataResult(ResultCodeEnum.FAIL);

    public DataResult(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum);
    }

    public DataResult(){}

    public DataResult(T data){
        setData(data);
        setCode(ResultCodeEnum.SUCCESS.getCode());
    }

    public DataResult(int code, String message){
        super(code,message);
    }

    public DataResult(ResultCodeEnum resultCodeEnum,T data){
        super(resultCodeEnum);
        setData(data);
    }

    public T getData() {
        return data;
    }



    public void setData(T data) {
        this.data = data;
    }



    @Override
    public String toString() {
        return super.toString() + "DataResult{" +
                "data=" + data +
                '}';
    }
}
