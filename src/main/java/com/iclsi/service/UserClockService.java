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
     * 插入一条用户云锁记录
     * @param userClock
     */
    public void inserUserClock(UserClock userClock) {
        userClockDao.insert(userClock);
    }

    /**
     * 通过userId查询用户相关联的所有云锁
     * @param userId
     * @return
     */
    @Transactional
    public List<Clock> queryClockByUserId(long userId) {
        return userClockDao.findClockByUserId(userId);
    }

    /**
     * 通过userId查询用户相关联的所有云锁
     * @param userId
     * @return
     */
    @Transactional
    public List<Clock> queryClockByUserIdAndAuthority(long userId, byte authority) {
        return userClockDao.findClockByUserIdAndAuthority(userId, authority);
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
     * 通过clockId查询相关联的高级用户
     * @param clockId
     * @return
     */
    @Transactional
    public List<User> queryVIPUserByClockId(long clockId) {
        return userClockDao.findVIPUserByClockId(clockId);
    }

    /**
     * 通过clockId查询相关联的普通用户
     * @param clockId
     * @return
     */
    @Transactional
    public List<User> queryNormalUserByClockId(long clockId) {
        return userClockDao.findNormalUserByClockId(clockId);
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

        // 云锁中存在该云锁
        if(clockDao.findById(clockId) == null) {
            return false;
        }

        // 用户表中存在该用户
        if(userDao.findById(userId) == null) {
            return false;
        }

        // 用户云锁表中不存在该云锁用户记录
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

    /**
     * 验证权限是管理员或高级用户
     * @param userId
     * @param clockId
     * @return false:普通用户 true:管理员或高级用户
     */
    public boolean AuthorityWithAdminAndVIP(long userId, long clockId) {
        if(userClockDao.findByClockIdAndUserId(clockId, userId) == null) {
            return false;
        }
        UserClock userClock = userClockDao.findByClockIdAndUserId(clockId, userId);
        if (userClock.getAuthority() == 2) {
            return false;
        }
        return true;
    }

    /**
     * 验证是否是管理员
     * @param userId
     * @param clockId
     * @return false:高级用户或普通用户 true:管理员
     */
    public boolean AuthorityWithAdmin(long userId, long clockId) {
        if(userClockDao.findByClockIdAndUserId(clockId, userId) == null) {
            return false;
        }
        UserClock userClock = userClockDao.findByClockIdAndUserId(clockId, userId);
        if (userClock.getAuthority() != 0) {
            return false;
        }
        return true;
    }
}
