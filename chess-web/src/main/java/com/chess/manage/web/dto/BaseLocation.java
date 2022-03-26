package com.chess.manage.web.dto;


import javax.validation.constraints.NotNull;


/**
 * @Description : 坐标
 * @Author : wukong
 * @Date: 2021/11/7 11:19
 */
public class BaseLocation {

    /**
     * X轴坐标
     */
    @NotNull(message = "X轴坐标不能为空")
    private Integer x;

    /**
     * Y轴坐标
     */
    @NotNull(message = "Y轴坐标不能为空")
    private Integer y;

    public BaseLocation() {
    }

    public BaseLocation(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

}
