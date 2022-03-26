package com.chess.manage.web.model;

import com.chess.manage.web.enums.RoleEnum;

import java.util.Date;

/**
 * @Description : 棋局落子明细信息
 * @Author : wukong
 * @Date: 2021/11/7 11:40
 */
public class ChessLocation {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * X轴坐标
     */
    private Integer x;

    /**
     * Y轴坐标
     */
    private Integer y;

    /**
     * 角色
     * @see RoleEnum
     */
    private Integer role;

    /**
     * 游戏编号
     */
    private Long gameId;

    /**
     * 新增时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updTime;

    /**
     * 是否有效
     */
    private Boolean isactive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
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

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }
}
