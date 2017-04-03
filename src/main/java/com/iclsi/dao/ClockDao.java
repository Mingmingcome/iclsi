package com.iclsi.dao;

import com.iclsi.entity.Clock;
import com.iclsi.entity.User;

import java.util.List;

/**
 * Created by luhaoming123 on 2016/12/22.
 */
public interface ClockDao {

    /**
     * 根据ID查询云锁信息
     * @param id
     * @return
     */
    public Clock findById(long id);

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
     * @param id
     */
    public void delete(long id);

    /**
     * 更新云锁
     * @param clock
     */
    public void update(Clock clock);

}
