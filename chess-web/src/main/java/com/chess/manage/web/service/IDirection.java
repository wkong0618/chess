package com.chess.manage.web.service;



import com.chess.manage.web.dto.Location;
import com.chess.manage.web.enums.RoleEnum;

import java.util.List;

/**
 * @Description : 五子棋连线方向
 * @Author : wukong
 * @Date: 2021/11/6 11:02
 */
public interface IDirection {

    /**
     * 不同方向获取坐标位置的连线分数(评估用于AI落子)
     * @param curChess 棋盘
     * @param x 将要落子的x坐标点
     * @param y 将要落子的y坐标点
     * @return
     */
    int getScore(int[][] curChess, int x, int y);

    /**
     * 不同方向获取坐标位置的连线分数(评估用于AI落子)
     * @param curChess 棋盘
     * @param curRole 执棋人
     * @param x 将要落子的x坐标点
     * @param y 将要落子的y坐标点
     * @return
     */
    boolean isWin(int[][] curChess, RoleEnum curRole, int x, int y);

    /**
     * 棋盘不同方向获取可落子点
     * @param chess 当前棋局
     * @param x X轴坐标
     * @param y Y轴坐标
     * @return
     */
    List<Location> mayLocations(int[][] chess, int x, int y);

}
