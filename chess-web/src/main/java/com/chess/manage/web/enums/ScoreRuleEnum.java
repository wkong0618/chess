package com.chess.manage.web.enums;

/**
 * @Description : 五子棋模拟下子分数评估规则
 * @Author : wukong
 * @Date: 2021/11/6 15:00
 */
public enum ScoreRuleEnum {
    /**
     * 得分规则枚举
     * --后期可优化:规则可使用分布式配置优化
     * 1.连续5个相同棋子,则不论左右(或者上下)阻断的状态,直接加20000分
     * 2. 连续2/3/4个相同棋子，根据连续相同棋子左右中断相邻为空位还是对方棋子添加分数权重
     */
    FIVE(5, -1, -1, 20000),

    FOUR_1(4, InterruptTypeEnum.NO.type(),InterruptTypeEnum.NO.type(), 5000),
    FOUR_2(4, InterruptTypeEnum.NO.type(),InterruptTypeEnum.OTHER.type(), 300),
    FOUR_3(4, InterruptTypeEnum.OTHER.type(),InterruptTypeEnum.OTHER.type(), 100),
    FOUR_4(4, InterruptTypeEnum.OTHER.type(),InterruptTypeEnum.NO.type(), 300),

    THREE_1(3, InterruptTypeEnum.NO.type(),InterruptTypeEnum.NO.type(), 300),
    THREE_2(3, InterruptTypeEnum.NO.type(),InterruptTypeEnum.OTHER.type(), 100),
    THREE_3(3, InterruptTypeEnum.OTHER.type(),InterruptTypeEnum.OTHER.type(), 50),
    THREE_4(3, InterruptTypeEnum.OTHER.type(),InterruptTypeEnum.NO.type(), 100),

    TWO_1(2, InterruptTypeEnum.NO.type(),InterruptTypeEnum.NO.type(), 50),
    TWO_2(2, InterruptTypeEnum.NO.type(),InterruptTypeEnum.OTHER.type(), 20),
    TWO_3(2, InterruptTypeEnum.OTHER.type(),InterruptTypeEnum.OTHER.type(), 10),
    TWO_4(2, InterruptTypeEnum.OTHER.type(),InterruptTypeEnum.NO.type(), 20),

    ONE_1(1, InterruptTypeEnum.NO.type(),InterruptTypeEnum.NO.type(), 10),
    ONE_2(1, InterruptTypeEnum.NO.type(),InterruptTypeEnum.OTHER.type(), 5),
    ONE_3(1, InterruptTypeEnum.OTHER.type(),InterruptTypeEnum.OTHER.type(), 3),
    ONE_4(1, InterruptTypeEnum.OTHER.type(),InterruptTypeEnum.NO.type(), 5),
    ;

    /**
     * 棋子连续相同数量
     */
    private final int continueCount;

    /**
     * 左边(或上面)中断标识
     */
    private final int lStatus;

    /**
     * 右边(或下面)中断标识
     */
    private final int rStatus;

    /**
     * 得分
     */
    private final int score;

    ScoreRuleEnum(int continueCount, int lStatus, int rStatus, int score) {
        this.continueCount = continueCount;
        this.lStatus = lStatus;
        this.rStatus = rStatus;
        this.score = score;
    }

    public static int getScore(int continueCount, RoleEnum curRole, int lStatus, int rStatus){
        if(FIVE.continueCount == continueCount){
            if(RoleEnum.AI.equals(curRole)){
                //AI赢的话加倍最大得分
                return FIVE.score + FIVE.score;
            }
            return FIVE.score;
        }

        for (ScoreRuleEnum item: ScoreRuleEnum.values()) {
            if(item.continueCount == continueCount
                    && item.lStatus == lStatus
                    && item.rStatus == rStatus){
                return item.score;
            }
        }

        return 0;
    }
}
