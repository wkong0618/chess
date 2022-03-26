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
 * @Description : 正斜向,从左上到右下(\)
 * @Author : wukong
 * @Date: 2021/11/6 11:15
 */
@Service
public class SkewForwardDirection implements IDirection {

    private static final Logger logger = LoggerFactory.getLogger(SkewForwardDirection.class);

    @Override
    public int getScore(int[][] curChess, int x, int y) {
        int score = getScore(curChess, x, y, RoleEnum.AI) + getScore(curChess, x, y, RoleEnum.PLAYER);
        logger.info("SkewForwardDirection-x:{},y:{},获取得分:{}", x, y, score);
        return score;
    }

    @Override
    public boolean isWin(int[][] curChess, RoleEnum curRole, int x, int y) {
        //相同连子数量
        int continueCount = 0;

        for (int i = x, j = y; i < curChess.length && j >= 0; i++, j--) {
            if (curChess[i][j] == curRole.role()) {
                continueCount++;
                continue;
            }
            break;
        }
        for (int i = x - 1, j = y + 1; i >= 0 && j < curChess.length; i--, j++) {
            if (curChess[i][j] == curRole.role()) {
                continueCount++;
                continue;
            }
            break;
        }

        if (continueCount >= DEFAULT_SUCCESS_CONTINUE_NUM) {
            return true;
        }
        return false;
    }

    @Override
    public List<Location> mayLocations(int[][] chess, int x, int y) {
        List<Location> mayLocations = new ArrayList<>();
        if (x != 0 && y != (chess.length - 1) && chess[x - 1][y + 1] == 0){
            mayLocations.add(new Location(x - 1, y + 1));
        }

        if (x != (chess.length - 1) && y != 0 && chess[x + 1][y - 1] == 0){
            mayLocations.add(new Location(x + 1, y - 1));
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
        if(x == 1 && y == 3){
         System.out.println("============");
        }
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
        //向右下遍历
        for (int i = x, j = y; i < curChess.length && j >= 0; i++, j--) {
            if(curChess[i][j] == curRole.role()){
                continueCount ++;
                continue;
            }

            if(curChess[i][j] == 0){
                lStatus = InterruptTypeEnum.NO.type();
                break;
            }

            if(curChess[i][j] == otherRole.role()){
                lStatus = InterruptTypeEnum.OTHER.type();
            }
            break;
        }

        //向左上遍历
        for (int i = x- 1, j = y + 1; i >= 0 && j < curChess.length; i--, j++) {
            if(curChess[i][j] == curRole.role()){
                continueCount ++;
                continue;
            }

            if(curChess[i][j] == 0){
                rStatus = InterruptTypeEnum.NO.type();
                break;
            }

            if(curChess[i][j] == otherRole.role()){
                rStatus = InterruptTypeEnum.OTHER.type();
            }
            break;
        }

        //模拟落子恢复
        curChess[x][y] = 0;
        return ScoreRuleEnum.getScore(continueCount, curRole, lStatus, rStatus);
    }
}
