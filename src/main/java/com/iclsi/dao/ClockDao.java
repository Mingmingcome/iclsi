package com.iclsi.dao;

import com.iclsi.entity.Clock;
import com.iclsi.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by luhaoming123 on 2016/12/22.
 */
@Repository
public interface ClockDao {

    /**
     * 根据锁ID查询云锁信息
     * @param clockId
     * @return
     */
    public Clock findById(long clockId);

    /**
     * 根据云锁名称name查询云锁信息
     * @param name
     * @return 拥有同名的云锁用List
     */
    public List<Clock> findByName(String name);

    /**
     * 添加云锁
     * @param clock
     */
    public void insert(Clock clock);

    /**
     * 根据ID删除云锁
     * @param clockId
     */
    public void delete(long clockId);

    /**
     * 更新云锁
     * @param clock
     */
    public void update(Clock clock);

    /**
     * 更新云锁状态
     * @param clockId
     */
    public void updateState(long clockId);

    /**
     * 查询所有的锁
     * @return
     */
    public List<Clock> queryAll();

}
