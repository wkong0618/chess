package com.chess.manage.web.controller;

import com.chess.manage.web.WebApplication;
import com.chess.manage.web.dto.*;
import com.chess.manage.web.model.common.DataResult;
import com.chess.manage.web.service.IChessService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 在此简单测试四个接口
 * 后期可补充校验条件，棋局模拟等test
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class ChessControllerTest {

    @Autowired
    private IChessService chessService;

    @Test
    public void startGame() {
        GameIdInfo gameIdInfo = chessService.startGame();
        Assert.assertNotNull("新开一局游戏gameId不能为空", gameIdInfo.getGameId());
    }

    @Test
    public void positions() {
        GameIdInfo gameIdInfo = chessService.startGame();
        DataResult<LocationRsp> dataResult = chessService.positions(gameIdInfo.getGameId(), new BaseLocation(3,3));
        Assert.assertNotNull(dataResult);
        Assert.assertEquals(200, dataResult.getCode());
        Assert.assertNotNull("获取结果不能为空", dataResult.getData());
    }

    @Test
    public void games() {
        GameInfo gameInfo = chessService.games();
        Assert.assertNotNull("棋局列表返回不能为空", gameInfo);
    }

    @Test
    public void qryGame() {
        GameIdInfo gameIdInfo = chessService.startGame();
        DataResult<LocationRsp> dataResult = chessService.positions(gameIdInfo.getGameId(), new BaseLocation(3,3));
        Assert.assertNotNull(dataResult);
        Assert.assertEquals(200, dataResult.getCode());
        Assert.assertNotNull(dataResult.getData());
        GameAllDetail allDetail = chessService.qryGame(gameIdInfo.getGameId());
        Assert.assertNotNull("单局棋局详情返回不能为空",allDetail);
    }
}