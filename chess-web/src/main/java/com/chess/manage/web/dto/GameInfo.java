package com.chess.manage.web.dto;

import java.util.List;

/**
 * @Description : 游戏集合信息
 * @Author : wukong
 * @Date: 2021/11/7 15:10
 */
public class GameInfo {
    /**
     * 棋局所有记录
     */
    private List<GameDetail> games;

    public GameInfo(List<GameDetail> games) {
        this.games = games;
    }

    public List<GameDetail> getGames() {
        return games;
    }

    public void setGames(List<GameDetail> games) {
        this.games = games;
    }
}
