package com.iclsi.entity;

/**
 * Created by luhaoming123 on 2016/12/22.
 */
public class Clock {

    private long clockId;

    private String name;

    private String password;

    private int state;

    public Clock() {
    }

    public Clock(long clockId, String name, String password) {
        this.clockId = clockId;
        this.name = name;
        this.password = password;
    }

    public Clock(long clockId, String name, String password, int state) {
        this.clockId = clockId;
        this.name = name;
        this.password = password;
        this.state = state;
    }

    public Clock(long clockId, String password) {
        this.clockId = clockId;
        this.password = password;
    }

    public long getClockId() {
        return clockId;
    }

    public void setClockId(long clockId) {
        this.clockId = clockId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Clock{" +
                "clockId=\'" + clockId +
                "\', name=\'" + name + '\'' +
                ", password=\'" + password + '\'' + ",state=\'" + state +
                '}';
    }
}
