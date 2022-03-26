package com.chess.manage.web.model;

import java.util.Date;

/**
 * @Description : 棋局信息
 * @Author : wukong
 * @Date: 2021/11/7 11:40
 */
public class ChessInfo {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 游戏编号
     */
    private Long gameId;

    /**
     * 用户ID - 暂用不到
     */
    private Long userId;

    /**
     * 是否完成
     */
    private Boolean complete;

    /**
     * 胜利者
     */
    private String winner;

    /**
     * 新增时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updTime;

    /**
     * 是否有效 0:无效 1：有效
     */
    private Boolean isactive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean isComplete() {
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

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    public Boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }
}
