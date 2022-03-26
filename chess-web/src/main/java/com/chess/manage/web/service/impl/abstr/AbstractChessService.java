package com.chess.manage.web.service.impl.abstr;

import com.chess.manage.web.dto.Location;
import com.chess.manage.web.enums.RoleEnum;
import com.chess.manage.web.mapper.ChessInfoMapper;
import com.chess.manage.web.mapper.ChessLocationMapper;
import com.chess.manage.web.model.ChessInfo;
import com.chess.manage.web.model.ChessLocation;
import com.chess.manage.web.service.IDirection;
import com.chess.manage.web.utils.Pair;
import com.chess.manage.web.utils.PubLogNo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description : 五子棋操作抽象类
 * @Author : wukong
 * @Date: 2021/11/6 11:19
 */
public abstract class AbstractChessService {

    /**
     * 默认胜利连续相同连子数,默认为5
     */
    public static final int DEFAULT_SUCCESS_CONTINUE_NUM = 5;

    /**
     * 棋盘轴长为20
     */
    public static final int CHESS_LENGTH = 20;

    @Autowired
    private List<IDirection> directionList;

    @Autowired
    private ChessInfoMapper chessInfoMapper;

    @Autowired
    private ChessLocationMapper chessLocationMapper;

    /**
     * 开启新的棋局
     * @return 棋局编号
     */
    public Long init() {
        //棋局编号,唯一(数字)
        Long gameId = PubLogNo.getPubLogNo();
        ChessInfo newGame = new ChessInfo();
        newGame.setGameId(gameId);
        chessInfoMapper.addGame(newGame);
        return gameId;
    }

    /**
     * 落子(AI或玩家)
     * @param x X轴坐标
     * @param y Y轴坐标
     * @param player 执棋标识
     * @param gameId 游戏编号
     * @return
     */
    public Pair<Boolean, String> checkLocationAt(int x, int y, int player, long gameId) {
        //获取棋盘落子情况转化为二维数组
        int[][] chessTmp = chess2Array(gameId);
        //边界判断
        if (x < 0 || x >= chessTmp.length
                || y < 0 || y >= chessTmp.length) {
            return new Pair<>(false, "落点有误");
        }

        //0为空位,非空判断
        if (chessTmp[x][y] != 0) {
            return new Pair<>(false, "位置非空");
        }
        return new Pair<>(true, null);
    }

    /**
     * 当前棋局是否落子已满
     * @param gameId 棋局编号
     * @return
     */
    public boolean isFull(long gameId){
        int[][] curChess = chess2Array(gameId);
        for (int i = 0; i < curChess.length; i++) {
            for (int j = 0; j < curChess.length; j++) {
                if(curChess[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 是否获胜
     * @param gameId 游戏场次
     * @param roleEnum 角色
     * @param x X轴坐标
     * @param y Y轴坐标
     * @return
     */
    public boolean isWin(long gameId, RoleEnum roleEnum, int x, int y){
        int[][] curChess = chess2Array(gameId);
        curChess[x][y] = roleEnum.role();
        for (IDirection direction:directionList) {
            if(direction.isWin(curChess, roleEnum, x, y)){
                return true;
            }
        }
        return false;
    }


    /**
     * AI根据当前棋面分析最合适的位置
     * @return Location 最合适的落子点
     */
    public Location analyzeBest(Long gameId, RoleEnum roleEnum, int x, int y) {
        // 可行位置的集合
        List<Location> allMayLocation = getMayLocations(gameId);
        int[][] curChess = chess2Array(gameId);
        curChess[x][y] = roleEnum.role();
        //计算可行性位置分数
        for (Location item:allMayLocation) {
            item.setScore(getScore(item, curChess));
        }

        allMayLocation = allMayLocation.stream().
                sorted(Comparator.comparing(Location::getScore).reversed()).
                collect(Collectors.toList());
        //返回分析的位置
        return allMayLocation.get(0);
    }

    /**
     * 获取下棋可能位置集合,对每个非空位置,将其四周的位置添加到集合中
     * @return 可下棋位置集合
     */
    private List<Location> getMayLocations(Long gameId) {
        int[][] curChess = chess2Array(gameId);
        List<Location> mayLocations = new ArrayList<>();
        // 根据当前已落子搜索棋盘获取可下棋的点
        for (int i = 0; i < curChess.length; i++) {
            for (int j = 0; j < curChess.length; j++) {
                if (curChess[i][j] != 0) {
                    for (IDirection direction:directionList) {
                        mayLocations.addAll(direction.mayLocations(curChess, i, j));
                    }
                }
            }
        }
        //去重
        mayLocations = mayLocations.stream().distinct().collect(Collectors.toList());
        return mayLocations;
    }

    /**
     * 根据位置获取得分
     * @param location
     * @return
     */
    private int getScore(Location location, int[][] curChess){
        //得分
        int score = 0;
        for (IDirection direction:directionList) {
            score += direction.getScore(curChess,
                    location.getX(), location.getY());
        }
        return score;
    }

    /**
     * 棋盘整体情况转化为二维数组
     * @param gameId
     * @return
     */
    public int[][] chess2Array(Long gameId){
        List<ChessLocation> locations = chessLocationMapper.qryLocations(gameId);
        int[][] chessArry = initChess();
        for (ChessLocation item:locations){
            chessArry[item.getX()][item.getY()] = item.getRole();
        }
        return chessArry;
    }

    /**
     * 初始化棋盘
     * @return
     */
    public int[][] initChess(){
        int[][] chessArry = new int[CHESS_LENGTH][CHESS_LENGTH];
        for (int i = 0; i < chessArry.length; i++) {
            for (int j = 0; j < chessArry.length; j++) {
                chessArry[i][j] = 0;
            }
        }
        return chessArry;
    }


}
