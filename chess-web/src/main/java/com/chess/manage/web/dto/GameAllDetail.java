package com.chess.manage.web.dto;

import java.util.List;

/**
 * @Description : 五子棋落子明细以及棋局状态
 * @Author : wukong
 * @Date: 2021/11/7 12:19
 */
public class GameAllDetail {
    /**
     * 棋局编号
     */
    private Long gameId;

    /**
     * 落子集合
     */
    private List<GamePosition> positions;

    /**
     * 是否结束
     */
    private Boolean complete;

    /**
     * 胜利者
     */
    private String winner;

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public List<GamePosition> getPositions() {
        return positions;
    }

    public void setPositions(List<GamePosition> positions) {
        this.positions = positions;
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
