package com.iclsi.dao;

import com.iclsi.entity.ClockRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public void deleteByClockId(long clockId);

    /**
     * 根据clockRecordId删除ClockRecord记录
     * @param clockRecordId
     */
    public void delete(long clockRecordId);

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
    public List<ClockRecord> findByClockId(long clockId);

    /**
     *  根据clockRecordId查询云锁开关记录
     * @param clockRecordId
     * @return
     */
    public List<ClockRecord> findByClockRecordId(long clockRecordId);
}
