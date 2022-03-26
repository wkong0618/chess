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
 * @Description : 纵向
 * @Author : wukong
 * @Date: 2021/11/6 11:07
 */
@Service
public class YDirection implements IDirection {

    private static final Logger logger = LoggerFactory.getLogger(YDirection.class);

    @Override
    public int getScore(int[][] curChess, int x, int y) {
        int score = getScore(curChess, x, y, RoleEnum.AI) + getScore(curChess, x, y, RoleEnum.PLAYER);
        logger.info("YDirection-x:{},y:{},获取得分:{}", x, y, score);
        return score;
    }

    @Override
    public boolean isWin(int[][] curChess, RoleEnum curRole, int x, int y) {
        //相同连子数量
        int continueCount = 0;

        for (int j = y; j < curChess.length; j++) {
            if (curChess[x][j] == curRole.role()) {
                continueCount ++;
                continue;
            }
            break;
        }

        for (int j = y - 1; j >= 0; j--) {
            if (curChess[x][j] == curRole.role()) {
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
        if(y != 0 && chess[x][y-1] == 0){
            mayLocations.add(new Location(x, y - 1));
        }
        if(y != chess.length - 1 && chess[x][y+1] == 0){
            mayLocations.add(new Location(x, y + 1));
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
        for (int j = y; j < curChess.length; j++) {
            if(curChess[x][j] == curRole.role()){
                continueCount ++;
                continue;
            }

            if(curChess[x][j] == 0){
                lStatus = InterruptTypeEnum.NO.type();
                break;
            }

            if(curChess[x][j] == otherRole.role()){
                lStatus = InterruptTypeEnum.OTHER.type();
            }
            break;
        }

        //向左遍历
        for (int j = y - 1; j >= 0; j--) {
            if(curChess[x][j] == curRole.role()){
                continueCount ++;
                continue;
            }

            if(curChess[x][j] == 0){
                rStatus = InterruptTypeEnum.NO.type();
                break;
            }

            if(curChess[x][j] == otherRole.role()){
                rStatus = InterruptTypeEnum.OTHER.type();
            }
            break;
        }

        //模拟落子恢复
        curChess[x][y] = 0;
        return ScoreRuleEnum.getScore(continueCount, curRole, lStatus, rStatus);
    }
}
