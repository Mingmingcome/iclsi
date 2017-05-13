package com.iclsi.service;

import com.iclsi.dao.ClockDao;
import com.iclsi.dao.UserClockDao;
import com.iclsi.dao.UserDao;
import com.iclsi.entity.Clock;
import com.iclsi.entity.User;
import com.iclsi.entity.UserClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by luhaoming123 on 2017/5/11.
 */
@Service
public class UserClockService {

    @Autowired
    private UserClockDao userClockDao;

    @Autowired
    private ClockDao clockDao;

    @Autowired
    private UserDao userDao;

    /**
     * 通过userId查询用户拥有的云锁
     * @param userId
     * @return
     */
    @Transactional
    public List<Clock> queryClockByUserId(long userId) {
        return userClockDao.findClockByUserId(userId);
    }

    /**
     * 通过clockId查询相关联的用户
     * @param clockId
     * @return
     */
    @Transactional
    public List<User> queryUserByClockId(long clockId) {
        return userClockDao.findUserByClockId(clockId);
    }

    /**
     * 添加云锁用户
     * @param userClock
     * @return false:添加云锁用户成功 true:添加云锁用户失败
     */
    @Transactional
    public boolean addUserClock(UserClock userClock) {
        long clockId = userClock.getClockId();
        long userId = userClock.getUserId();
        if(clockDao.findById(clockId) == null) {
            return false;
        }
        if(userDao.findById(userId) == null) {
            return false;
        }
        if(userClockDao.findByClockIdAndUserId(clockId, userId) == null) {

            userClockDao.insert(userClock);
            return true;
        }

        return false;
    }

    /**
     * 修改云锁用户权限
     * @param userClock
     * @return
     */
    @Transactional
    public boolean modifyAuthority(UserClock userClock) {
        long clockId = userClock.getClockId();
        long userId = userClock.getUserId();
        UserClock userClock1 = userClockDao.findByClockIdAndUserId(clockId, userId);

        if(userClock1 != null) {
            userClock1.setAuthority(userClock.getAuthority());
            userClockDao.update(userClock1);
            return true;
        }

        return false;
    }

    /**
     * 根据clockId和userId删除UserClock记录
     * @param clockId
     * @param userId
     * @return
     */
    @Transactional
    public boolean deleteUserClock(long clockId, long userId) {
        if(userClockDao.findByClockIdAndUserId(clockId, userId) != null) {

            userClockDao.deleteByClockIdAndUserId(clockId, userId);
            return true;
        }

        return false;
    }

}
