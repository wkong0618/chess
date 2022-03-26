package com.chess.manage.web.enums;

/**
 * @Description : 角色
 * @Author : wukong
 * @Date: 2021/11/6 14:04
 */
public enum RoleEnum {
    /**
     * 五子棋角色,其中role也为下在棋盘上的标识
     */
    PLAYER(1, "user"),
    AI(2, "AI");

    /**
     * 角色标识
     */
    private final int role;

    /**
     * 描述
     */
    private final String desc;

    RoleEnum(int role, String desc) {
        this.role = role;
        this.desc = desc;
    }

    public int role() {
        return role;
    }

    public String desc() {
        return desc;
    }

    public static RoleEnum byRule(int role) {
        for (RoleEnum item : values()) {
            if (item.role == role) {
                return item;
            }
        }
        return null;
    }
}
