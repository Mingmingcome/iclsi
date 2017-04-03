package com.iclsi.entity;

/**
 * Created by luhaoming123 on 2016/12/22.
 */
public class Clock {

    private long clockId;

    private String name;

    private String password;

    public Clock() {
    }

    public Clock(long clockId, String name, String password) {
        this.clockId = clockId;
        this.name = name;
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

    @Override
    public String toString() {
        return "Clock{" +
                "clockId=" + clockId +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
