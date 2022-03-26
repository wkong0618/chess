package com.chess.manage.web.mapper;

import com.chess.manage.web.model.ChessLocation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description : 棋局落子明细信息
 * @Author : wukong
 * @Date: 2021/11/7 11:30
 */
@Repository
public interface ChessLocationMapper {

    /**
     * 获取落子点
     * @param gameId 游戏编号
     * @return List<ChessLocation> 落子集合
     */
    List<ChessLocation> qryLocations(@Param("gameId") Long gameId);

    /**
     * 添加落子点
     * @param location
     * @return int
     */
    int addLocation(ChessLocation location);
}
