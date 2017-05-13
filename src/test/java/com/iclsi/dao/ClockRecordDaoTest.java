package com.iclsi.dao;

import com.iclsi.entity.ClockRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by luhaoming123 on 2017/4/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class ClockRecordDaoTest {

    @Autowired
    ClockRecordDao clockRecordDao;

    @Test
    public void insert() throws Exception {
        ClockRecord clockRecord = new ClockRecord(1,1,0);
        clockRecordDao.insert(clockRecord);
    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void findByClockId() throws Exception {
        List<ClockRecord> clockRecords = clockRecordDao.findByClockId(1);
        System.out.println(clockRecords);
    }

}