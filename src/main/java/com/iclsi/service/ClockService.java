package com.iclsi.service;

import com.iclsi.dao.ClockDao;
import com.iclsi.dao.ClockRecordDao;
import com.iclsi.dao.UserClockDao;
import com.iclsi.entity.Clock;
import com.iclsi.entity.ClockRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * Created by luhaoming123 on 2017/5/1.
 */
@Service
public class ClockService {

    @Autowired
    private ClockDao clockDao;

    @Autowired
    private ClockRecordDao clockRecordDao;

    @Autowired
    private UserClockDao userClockDao;

    private final String salt = "1234567890!@#$%^&*()QWER";

    /**
     * 根据clockId查询该clock是否被注册过
     *
     * @param clockId
     * @return 1:clockId已经被注册过 0:clockId没有被注册过
     */
    public int queryClockByClockId(long clockId) {
        if(clockDao.findById(clockId) == null) {
            return 0;
        }
        return 1;
    }

    /**
     *
     * @param clockId
     * @param password
     * @return -1:clock已经被注册过 0:clock注册不成功 1:clock注册成功
     */
    @Transactional
    public int register(long clockId, String password) {
        if(queryClockByClockId(clockId) == 0) {
            password = getMD5(password);
            Clock clock = new Clock(clockId, password);
            clockDao.insert(clock);
        }else {
            return -1;
        }
        return queryClockByClockId(clockId);
    }

    /**
     *
     * @param origin 原始值
     * @return String 加密后的字符串
     */
    private String getMD5(String origin) {
        String base = origin + "/" + salt;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    /**
     * 查询所有的锁
     * @return
     */
    public List<Clock> queryAll() {
        return clockDao.queryAll();
    }

    /**
     * 开锁并记录
     * @param clockId
     * @param userId
     * @return false:开锁失败 true:开锁成功
     */
    @Transactional
    public boolean openClock(long clockId, long userId) {
        // TODO: 2017/5/15
        // 在这里应该加上UserClock中authority的判断
        // 如果authority=0或者authority=1，可以执行开锁操作

        Clock clock = clockDao.findById(clockId);
        if(clock.getState() == 0) {
            clock.setState(1);
            clockDao.update(clock);
            ClockRecord clockRecord = new ClockRecord(userId, clockId, clock.getState());
            clockRecordDao.insert(clockRecord);
            return true;
        }

        return false;
    }

    /**
     * 上锁并记录
     * @param clockId
     * @param userId
     * @return false:上锁失败 true:上锁成功
     */
    @Transactional
    public boolean closeClock(long clockId, long userId) {
        Clock clock = clockDao.findById(clockId);
        if(clock.getState() == 1) {
            clock.setState(0);
            clockDao.update(clock);
            ClockRecord clockRecord = new ClockRecord(userId, clockId, clock.getState());
            clockRecordDao.insert(clockRecord);
            return true;
        }

        return false;
    }

    @Transactional
    public boolean modifyPassword(Clock clock) {
        Clock updateClock = clockDao.findById(clock.getClockId());

        if(!updateClock.getPassword().equals(getMD5(clock.getPassword()))) {
            updateClock.setPassword(getMD5(clock.getPassword()));
            clockDao.update(updateClock);
            return true;
        }

        return false;
    }

    /**
     * 删除云锁
     * @param clockId
     * @return
     */
    @Transactional
    public boolean deleteClock(long clockId) {

        try {
            // 删除用户-云锁表记录
            userClockDao.delete(clockId);

            // 删除云锁开关记录表记录
            clockRecordDao.deleteByClockId(clockId);

            // 删除云锁表记录
            clockDao.delete(clockId);

            return true;
        }catch(Exception e) {
            System.out.println("删除云锁出错！");
        }
        return false;
    }

}
