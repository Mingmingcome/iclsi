package com.iclsi.dao;

import com.iclsi.entity.Clock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by luhaoming123 on 2017/4/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class ClockDaoTest {

    @Autowired
    private ClockDao clockDao;

    @Test
    public void findById() throws Exception {
        long id = 2;
        Clock clock = clockDao.findById(id);
        assertEquals("mingming", clock.getName());

    }

    @Test
    public void insert() throws Exception {
        Clock clock = new Clock(3,"mingming","password",0);
        clockDao.insert(clock);
    }

    @Test
    public void update() throws Exception {
        Clock clock = new Clock(2,"mingming","password",0);
        clockDao.update(clock);
    }

    @Test
    public void queryAll() throws Exception {
        List<Clock> clock = clockDao.queryAll();
        assertEquals(clock.get(0).getName(),"mingming");
    }

    @Test
    public void delete() throws Exception {
        clockDao.delete(2);
        assertEquals(clockDao.findById(2),null);
    }

}