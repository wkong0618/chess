package com.chess.manage.web.dto;


/**
 * @Description : 五子棋状态
 * @Author : wukong
 * @Date: 2021/11/7 13:10
 */
public class GameDetail {
    /**
     * 游戏编号
     */
    private Long id;

    /**
     * 是否完成
     */
    private Boolean complete;

    /**
     * 胜利者
     */
    private String winner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
