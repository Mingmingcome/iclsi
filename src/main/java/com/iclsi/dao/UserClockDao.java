package com.iclsi.dao;

import com.iclsi.entity.Clock;
import com.iclsi.entity.User;
import com.iclsi.entity.UserClock;

import java.util.List;

/**
 * Created by luhaoming123 on 2016/12/22.
 */
public interface UserClockDao {

    /**
     * 根据用户名username查找用户拥有的云锁
     * @param id
     * @return
     */
    public List<Clock> findClockByUsername(long id);

    /**
     * 根据云锁id查询拥有使用权的用户
     * @param id
     * @return
     */
    public List<User> findUserByClockId(long id);

    /**
     * 添加用户-云锁记录
     * @param
     */
    public void insert(UserClock userClock);

    /**
     * 根据ID删除用户-云锁记录
     * @param id
     */
    public void delete(long id);

    /**
     * 更新用户-云锁表
     * @param userClock
     */
    public void update(UserClock userClock);

}
