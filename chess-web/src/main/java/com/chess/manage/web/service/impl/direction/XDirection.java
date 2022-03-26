package com.chess.manage.web.service.impl.direction;


import com.chess.manage.web.dto.Location;
import com.chess.manage.web.enums.InterruptTypeEnum;
import com.chess.manage.web.enums.RoleEnum;
import com.chess.manage.web.enums.ScoreRuleEnum;
import com.chess.manage.web.service.IDirection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.chess.manage.web.service.impl.abstr.AbstractChessService.DEFAULT_SUCCESS_CONTINUE_NUM;


/**
 * @Description : 横向
 * @Author : wukong
 * @Date: 2021/11/6 11:07
 */
@Service
public class XDirection implements IDirection {

    private static final Logger logger = LoggerFactory.getLogger(XDirection.class);

    @Override
    public int getScore(int[][] curChess, int x, int y) {
        int score = getScore(curChess, x, y, RoleEnum.AI) + getScore(curChess, x, y, RoleEnum.PLAYER);
        logger.info("XDirection-x:{},y:{},获取得分:{}", x, y, score);
        return score;
    }

    @Override
    public boolean isWin(int[][] curChess, RoleEnum curRole, int x, int y) {
        //相同连子数量
        int continueCount = 0;

        for (int i = x; i < curChess.length; i++) {
            if (curChess[i][y] == curRole.role()) {
                continueCount ++;
                continue;
            }
            break;
        }

        for (int i = x - 1; i >= 0; i--) {
            if (curChess[i][y] == curRole.role()) {
                continueCount ++;
                continue;
            }
            break;
        }

        if(continueCount >= DEFAULT_SUCCESS_CONTINUE_NUM){
            return true;
        }

        return false;
    }

    @Override
    public List<Location> mayLocations(int[][] chess, int x, int y) {
        List<Location> mayLocations = new ArrayList<>();
        if(x != 0 && chess[x - 1][y] == 0){
            mayLocations.add(new Location(x - 1, y));
        }
        if(x != chess.length - 1 && chess[x + 1][y] == 0){
            mayLocations.add(new Location(x + 1, y));
        }
        return mayLocations;
    }

    /**
     * 评估分数
     * @param curChess 当前棋盘
     * @param x 横轴坐标
     * @param y 纵轴坐标
     * @param curRole 当前下棋角色
     * @return
     */
    private int getScore(int[][] curChess, int x, int y, RoleEnum curRole){
        RoleEnum otherRole;
        if(RoleEnum.AI.equals(curRole)){
            otherRole = RoleEnum.PLAYER;
        } else {
            otherRole = RoleEnum.AI;
        }

        //模拟落子,计算分数
        curChess[x][y] = curRole.role();

        //左右两侧中断状态,默认都为越界
        int lStatus = InterruptTypeEnum.INIT.type();
        int rStatus = InterruptTypeEnum.INIT.type();

        //相同连子数量
        int continueCount = 0;
        //向右遍历
        for (int j = x; j < curChess.length; j++) {
            if(curChess[j][y] == curRole.role()){
                continueCount ++;
                continue;
            }

            if(curChess[j][y] == 0){
                lStatus = InterruptTypeEnum.NO.type();
                break;
            }

            if(curChess[j][y] == otherRole.role()){
                lStatus = InterruptTypeEnum.OTHER.type();
            }
            break;
        }

        //向左遍历
        for (int j = x - 1; j >= 0; j--) {
            if(curChess[j][y] == curRole.role()){
                continueCount ++;
                continue;
            }

            if(curChess[j][y] == 0){
                rStatus = InterruptTypeEnum.NO.type();
                break;
            }

            if(curChess[j][y] == otherRole.role()){
                rStatus = InterruptTypeEnum.OTHER.type();
            }
            break;
        }

        //模拟落子恢复
        curChess[x][y] = 0;
        return ScoreRuleEnum.getScore(continueCount, curRole, lStatus, rStatus);
    }
}
