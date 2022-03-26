package com.chess.manage.web.service.impl;

import com.chess.manage.web.dto.*;
import com.chess.manage.web.enums.ResultCodeEnum;
import com.chess.manage.web.enums.RoleEnum;
import com.chess.manage.web.mapper.ChessInfoMapper;
import com.chess.manage.web.mapper.ChessLocationMapper;
import com.chess.manage.web.model.ChessLocation;
import com.chess.manage.web.model.common.DataResult;
import com.chess.manage.web.service.IChessService;
import com.chess.manage.web.service.impl.abstr.AbstractChessService;
import com.chess.manage.web.utils.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description : 五子棋实现类
 * @Author : wukong
 * @Date: 2021/11/6 10:51
 */
@Service
public class ChessServiceImpl extends AbstractChessService implements IChessService {

    @Autowired
    private ChessInfoMapper chessInfoMapper;

    @Autowired
    private ChessLocationMapper chessLocationMapper;

    @Override
    public GameIdInfo startGame() {
        GameIdInfo gameIdInfo = new GameIdInfo();
        gameIdInfo.setGameId(init());
        return gameIdInfo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public DataResult<LocationRsp> positions(Long gameId, BaseLocation location) {
        DataResult<LocationRsp> dataResult = new DataResult<>();
        LocationRsp locationRsp = new LocationRsp();
        //校验落子位置
        Pair<Boolean, String> checkPair =  checkLocationAt(location.getX(),
                location.getY(), RoleEnum.PLAYER.role(), gameId);
        if(!checkPair.getKey()){
            dataResult.setCode(ResultCodeEnum.FAIL.getCode());
            dataResult.setMessage(checkPair.getValue());
            return dataResult;
        }

        //棋局当前落子是否还有落子位置
        if(isFull(gameId)){
            locationRsp.setComplete(true);
            locationRsp.setWinner("none");
            return new DataResult<>(locationRsp);
        }

        //玩家落子->DB
        ChessLocation addLocation = new ChessLocation();
        addLocation.setGameId(gameId);
        addLocation.setRole(RoleEnum.PLAYER.role());
        addLocation.setX(location.getX());
        addLocation.setY(location.getY());
        chessLocationMapper.addLocation(addLocation);
        //用户玩家是否胜利
        boolean isPlayerWin = isWin(gameId, RoleEnum.PLAYER, location.getX(), location.getY());
        if(isPlayerWin){
            //更新棋局状态以及胜利者
            chessInfoMapper.updateGameStatus(true, RoleEnum.PLAYER.desc(), gameId);
            locationRsp.setComplete(true);
            locationRsp.setWinner(RoleEnum.PLAYER.desc());
            return new DataResult<>(locationRsp);
        }

        //AI探索评估落子点
        Location aiBestLocation = analyzeBest(gameId, RoleEnum.PLAYER, location.getX(), location.getY());
        //AI落子->DB
        ChessLocation aiLocation = new ChessLocation();
        aiLocation.setGameId(gameId);
        aiLocation.setRole(RoleEnum.AI.role());
        aiLocation.setX(aiBestLocation.getX());
        aiLocation.setY(aiBestLocation.getY());
        chessLocationMapper.addLocation(aiLocation);
        //AI是否取胜
        boolean isAiWin = isWin(gameId, RoleEnum.AI, aiBestLocation.getX(), aiBestLocation.getY());
        if(isAiWin){
            //更新棋局状态以及胜利者
            chessInfoMapper.updateGameStatus(true, RoleEnum.PLAYER.desc(), gameId);
            locationRsp.setComplete(true);
            locationRsp.setWinner(RoleEnum.AI.desc());
            locationRsp.setAiNextPosition(new BaseLocation(aiBestLocation.getX(), aiBestLocation.getY()));
            return new DataResult<>(locationRsp);
        }

        //AI下一位置
        locationRsp.setComplete(false);
        locationRsp.setAiNextPosition(new BaseLocation(aiBestLocation.getX(), aiBestLocation.getY()));
        return new DataResult<>(locationRsp);
    }

    @Override
    public GameInfo games() {
        List<GameDetail> games = chessInfoMapper.qryAllGames();
        return new GameInfo(games);
    }

    @Override
    public GameAllDetail qryGame(Long gameId) {
        GameAllDetail gameAllDetail = new GameAllDetail();
        GameDetail gameDetail = chessInfoMapper.qryGame(gameId);
        List<ChessLocation> locations = chessLocationMapper.qryLocations(gameId);

        //封装落子集合
        List<GamePosition> positions = new ArrayList<>();
        GamePosition gamePosition;
        BaseLocation baseLocation;
        for (ChessLocation location:locations){
            gamePosition = new GamePosition();
            baseLocation = new BaseLocation();
            RoleEnum roleEnum = RoleEnum.byRule(location.getRole());
            gamePosition.setFrom(null ==  roleEnum ? null : roleEnum.desc());
            baseLocation.setX(location.getX());
            baseLocation.setY(location.getY());
            gamePosition.setPosition(baseLocation);
            positions.add(gamePosition);
        }

        gameAllDetail.setGameId(gameId);
        gameAllDetail.setWinner(gameDetail.getWinner());
        gameAllDetail.setComplete(gameDetail.getComplete());
        gameAllDetail.setPositions(positions);
        return gameAllDetail;
    }
}
