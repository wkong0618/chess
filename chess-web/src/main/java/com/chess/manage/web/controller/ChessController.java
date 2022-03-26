package com.chess.manage.web.controller;

import com.chess.manage.web.dto.*;
import com.chess.manage.web.model.common.DataResult;
import com.chess.manage.web.service.IChessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Description : 五子棋
 * @Author : wukong
 * @Date: 2021/11/6 10:49
 */
@RestController
@RequestMapping("")
public class ChessController {

    @Autowired
    private IChessService chessService;

    /**
     * 开启新的一局游戏
     * @return GameIdInfo
     */
    @PostMapping("/games")
    public GameIdInfo startGame(){
        return chessService.startGame();
    }

    /**
     * 用户落子
     * @return
     */
    @PostMapping("/games/{gameId}/positions")
    public DataResult<LocationRsp> positions(@PathVariable Long gameId, @RequestBody @Valid BaseLocation location){
        return chessService.positions(gameId, location);
    }

    /**
     * 棋局列表
     * @return GameInfo
     */
    @GetMapping("/games")
    public GameInfo games(){
        return chessService.games();
    }

    /**
     * 某一局详情
     * @return GameIdInfo
     */
    @GetMapping("/games/{gameId}")
    public GameAllDetail qryGame(@PathVariable Long gameId){
        return chessService.qryGame(gameId);
    }

}
