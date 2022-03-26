package com.chess.manage.web.dto;


/**
 * @Description : 游戏落子明细
 * @Author : wukong
 * @Date: 2021/11/7 17:30
 */
public class GamePosition {

    /**
     * 落子来自方
     */
    private String from;

    /**
     * 落子点
     */
    private BaseLocation position;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public BaseLocation getPosition() {
        return position;
    }

    public void setPosition(BaseLocation position) {
        this.position = position;
    }
}
