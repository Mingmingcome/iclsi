package com.iclsi.dao;

import com.iclsi.entity.Clock;
import com.iclsi.entity.User;
import com.iclsi.entity.UserClock;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by luhaoming123 on 2016/12/22.
 */
@Repository
public interface UserClockDao {

    /**
     * 通过userClockId查找UserClock表记录
     * @param userClockId
     * @return
     */
    public UserClock findByUserClockId(long userClockId);

    /**
     * 根据用户名userId查找用户拥有的云锁
     * @param userId
     * @return
     */
    public List<Clock> findClockByUserId(long userId);

    /**
     * 根据云锁id查询相关联的用户
     * @param clockId
     * @return
     */
    public List<User> findUserByClockId(long clockId);

    /**
     * 通过clockId和userId查询UserClock记录
     * @param clockId
     * @param userId
     * @return
     */
    public UserClock findByClockIdAndUserId(@Param("clockId") long clockId, @Param("userId") long userId);

    /**
     * 添加用户-云锁记录
     * @param
     */
    public void insert(UserClock userClock);

    /**
     * 根据clockId和userId删除UserClock记录
     * @param clockId
     * @param userId
     */
    public void deleteByClockIdAndUserId(@Param("clockId") long clockId, @Param("userId") long userId);

    /**
     * 根据clockId删除UserClock记录
     * @param clockId
     */
    public void delete(long clockId);

    /**
     * 更新用户-云锁表
     * @param userClock
     */
    public void update(UserClock userClock);

}
