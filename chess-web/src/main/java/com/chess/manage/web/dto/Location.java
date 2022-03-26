package com.chess.manage.web.dto;


import com.chess.manage.web.enums.RoleEnum;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Description : 棋子位置
 * @Author : wukong
 * @Date: 2021/11/6 11:28
 */
public class Location implements Serializable {
    /**
     * X轴坐标
     */
    private int x;

    /**
     * Y轴坐标
     */
    private int y;

    /**
     * 落子所有者
     * @see RoleEnum
     */
    private int player;

    /**
     * 分数
     */
    private int score;

    /**
     * 棋局编号,每局都是唯一的
     */
    private long gameId;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x && y == location.y && player == location.player && score == location.score && gameId == location.gameId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, player, score, gameId);
    }
}
