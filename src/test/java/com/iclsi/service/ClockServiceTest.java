package com.iclsi.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by luhaoming123 on 2017/5/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"
})
public class ClockServiceTest {

    @Autowired
    private ClockService clockService;

    @Test
    public void queryClockByClockId() throws Exception {
        int result = clockService.queryClockByClockId(1);
        assertEquals(0,result);
        int result1 = clockService.queryClockByClockId(2);
        assertEquals(1,result1);
    }

    @Test
    public void register() throws Exception {
        long clockId = 1234;
        String password = "mingming";
        int result = clockService.register(clockId,password);
        int result1 = clockService.queryClockByClockId(clockId);
        assertEquals(result,result1);
    }

}