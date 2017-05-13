package com.iclsi.service;

import com.iclsi.dao.ClockRecordDao;
import com.iclsi.entity.ClockRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luhaoming123 on 2017/5/11.
 */
@Service
public class ClockRecordService {

    @Autowired
    private ClockRecordDao clockRecordDao;

    /**
     * 通过clockId查询开关锁记录
     * @param clockId
     * @return
     */
    public List<ClockRecord> queryClockRecordByClockId(long clockId) {
        return clockRecordDao.findByClockId(clockId);
    }

    /**
     * 根据clockId删除云锁开关记录
     * @param clockId
     * @return false:
     */
    public boolean deleteByClockId(long clockId) {
        if(clockRecordDao.findByClockId(clockId).size() == 0) {
            return false;
        }
        try {
            clockRecordDao.deleteByClockId(clockId);
            return true;
        }catch (Exception e) {
            System.out.println("删除云锁开关记录出错！");
        }

        return false;
    }

    /**
     * 根据clockRecordId删除云锁开关记录
     * @param clockRecordId
     * @return
     */
    public boolean deleteByClockRecordId(long clockRecordId) {
        if(clockRecordDao.findByClockRecordId(clockRecordId).size() == 0) {
            return false;
        }
        try {
            clockRecordDao.delete(clockRecordId);
            return true;
        }catch (Exception e) {
            System.out.println("删除云锁开关记录出错！");
        }

        return false;
    }

}
