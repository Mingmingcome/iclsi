package com.iclsi.dao;

import com.iclsi.entity.ClockRecord;
import org.springframework.stereotype.Repository;

/**
 * Created by luhaoming123 on 2017/4/26.
 */
@Repository
public interface ClockRecordDao {

    /**
     * 添加云锁开关记录
     * @param clockRecord
     */
    public void insert(ClockRecord clockRecord);

    /**
     * 根据云锁ID删除云锁开关记录
     * @param clockId
     */
    public void delete(long clockId);

    /**
     * 更新云锁记录
     * @param clockRecord
     */
    public void update(ClockRecord clockRecord);

    /**
     *  根据云锁ID查询云锁开关记录
     * @param clockId
     * @return
     */
    public ClockRecord findById(long clockId);
}
