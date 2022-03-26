package com.chess.manage.web.dto;

/**
 * @Description : 五子棋编号信息
 * @Author : wukong
 * @Date: 2021/11/7 14:10
 */
public class GameIdInfo {
    /**
     * 棋局编号
     */
    private Long gameId;

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
}
