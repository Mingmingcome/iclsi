package com.iclsi.dao;

import com.iclsi.entity.UserClock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by luhaoming123 on 2017/5/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class UserClockDaoTest {

    @Autowired
    private UserClockDao userClockDao;

    @Test
    public void findClockByUserId() throws Exception {
        long userId = 1;
        userClockDao.findClockByUserId(userId);
    }

    @Test
    public void findUserByClockId() throws Exception {

    }

    @Test
    public void findByClockIdAndUserId() throws Exception {
        UserClock userClock = userClockDao.findByClockIdAndUserId(121,3);
        System.out.println(userClock);
    }

    @Test
    public void insert() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

}