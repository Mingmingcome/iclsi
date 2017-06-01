package com.iclsi.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by luhaoming123 on 2017/4/26.
 */
public class ClockRecord {

    private long clockRecordId;

    private long userId;

    private long clockId;

    private String time;

    private int state;

    public ClockRecord() {
    }

    public ClockRecord(long clockRecordId, long userId, long clockId, String time, int state) {
        this.clockRecordId = clockRecordId;
        this.userId = userId;
        this.clockId = clockId;
        this.time = time;
        this.state = state;
    }

    public ClockRecord(long userId, long clockId, String time, int state) {
        this.userId = userId;
        this.clockId = clockId;
        this.time = time;
        this.state = state;
    }

    public ClockRecord(long userId, long clockId, int state) {
        this.userId = userId;
        this.clockId = clockId;
        this.state = state;
    }

    public long getClockRecordId() {
        return clockRecordId;
    }

    public void setClockRecordId(long clockRecordId) {
        this.clockRecordId = clockRecordId;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ClockRecord{" +
                "clockRecordId=\'" + clockRecordId +
                "\', userId=\'" + userId + '\'' +
                ", time=\'" + time + '\'' + ",state=\'" + state +
                "\'}";
    }

}
