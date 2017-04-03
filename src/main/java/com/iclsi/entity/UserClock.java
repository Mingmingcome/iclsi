package com.iclsi.entity;

/**
 * Created by luhaoming123 on 2016/12/22.
 */
public class UserClock {

    private long userClockId;

    private long userId;

    private long clockId;

    private byte authority;

    public UserClock() {
    }

    public UserClock(long userClockId, long userId, long clockId) {
        this.userClockId = userClockId;
        this.userId = userId;
        this.clockId = clockId;
    }

    public long getUserClockId() {
        return userClockId;
    }

    public void setUserClockId(long userClockId) {
        this.userClockId = userClockId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getClockId() {
        return clockId;
    }

    public void setClockId(long clockId) {
        this.clockId = clockId;
    }

    public byte getAuthority() {
        return authority;
    }

    public void setAuthority(byte authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "UserClock{" +
                "userClockId=" + userClockId +
                ", userId=" + userId +
                ", clockId=" + clockId +
                ", authority=" + authority +
                '}';
    }
}
