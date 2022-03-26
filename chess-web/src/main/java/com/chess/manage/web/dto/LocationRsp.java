package com.chess.manage.web.dto;

/**
 * @Description : 游戏落子后响应
 * @Author : wukong
 * @Date: 2021/11/7 18:30
 */
public class LocationRsp {

    /**
     * 是否结束
     */
    private Boolean complete;

    /**
     * 胜利者
     */
    private String winner;

    /**
     * AI 下一步要走的棋
     */
    private BaseLocation aiNextPosition;

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public BaseLocation getAiNextPosition() {
        return aiNextPosition;
    }

    public void setAiNextPosition(BaseLocation aiNextPosition) {
        this.aiNextPosition = aiNextPosition;
    }
}
