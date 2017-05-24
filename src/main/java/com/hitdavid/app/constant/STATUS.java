package com.hitdavid.app.constant;

/**
 * Created by David on 2017/5/22.
 */
public enum STATUS {

    USER_NORMAL(101),
    USER_BLOCKED(10001),

    ROLE_NORMAL(201),
    ROLE_BLOCKED(20001);

    private int value;

    STATUS(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
