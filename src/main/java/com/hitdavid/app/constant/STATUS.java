package com.hitdavid.app.constant;

/**
 * Created by David on 2017/5/22.
 */
public enum STATUS {

    USER_NORMAL(101),
    ROLE_NORMAL(201),
    RES_NORMAL(301),

    BLOCKED_STATUS_GROUP(10000),
    USER_BLOCKED(10001),
    ROLE_BLOCKED(20001),
    RES_BLOCKED(30001),

    MAX_VALUE_STATUS_GROUP(100000);


    private int value;

    STATUS(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
