package com.chess.manage.web.mapper;

import com.chess.manage.web.dto.GameDetail;
import com.chess.manage.web.model.ChessInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description : 棋局信息
 * @Author : wukong
 * @Date: 2021/11/7 11:30
 */
@Repository
public interface ChessInfoMapper {
    /**
     * 增加新的游戏记录
     * @param chessInfo
     * @return
     */
    int addGame(ChessInfo chessInfo);

    /**
     * 查询所有的游戏情况
     * @return
     */
    List<GameDetail> qryAllGames();

    /**
     * 查询棋局详情
     * @param gameId 棋局编号
     * @return
     */
    GameDetail qryGame(@Param("gameId") Long gameId);

    /**
     * 更新棋局状态
     * @param complete 是否结束 true:是 false: 否
     * @param winner 胜利方
     * @param gameId 棋局编号
     * @return
     */
    int updateGameStatus(@Param("complete") Boolean complete, @Param("winner") String winner, @Param("gameId") Long gameId);
}
