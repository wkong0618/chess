package com.chess.manage.web.enums;

/**
 * @Description : 棋子连续被中断类型
 * @Author : wukong
 * @Date: 2021/11/6 14:31
 */
public enum InterruptTypeEnum {
    /**
     * 五子棋连续相同棋子被中断的相邻位置状况
     */
    INIT(0, "初始"),
    NO(1, "空位"),
    OTHER(2, "对方棋子")
    ;

    /**
     * 角色标识
     */
    private final int type;

    /**
     * 描述
     */
    private final String desc;

    InterruptTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int type() {
        return type;
    }

    public String desc() {
        return desc;
    }
}
