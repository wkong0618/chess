package com.chess.manage.web.service;

import com.chess.manage.web.dto.*;
import com.chess.manage.web.model.common.DataResult;

/**
 * @Description : 五子棋接口
 * @Author : wukong
 * @Date: 2021/11/6 10:50
 */
public interface IChessService {

    /**
     * 开启新的一局游戏
     * @return
     */
    GameIdInfo startGame();

    /**
     * 玩家落子
     * @param gameId 棋局编号
     * @param location 落子点
     * @return
     */
    DataResult<LocationRsp> positions(Long gameId, BaseLocation location);

    /**
     * 棋局列表
     * @return
     */
    GameInfo games();

    /**
     * 某一局落子详情
     * @param gameId
     * @return
     */
    GameAllDetail qryGame(Long gameId);
}
