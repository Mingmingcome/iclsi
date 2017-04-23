package com.iclsi.service;

import com.iclsi.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by luhaoming123 on 2017/4/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"
})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void queryUserByEmail() throws Exception {
        String email = "1239@qq.com";
        int result = userService.queryUserByEmail(email);
        assertEquals(0,result);
        String email1 = "13712@qq.com";
        int result1 = userService.queryUserByEmail(email1);
        assertEquals(1,result1);
    }

    @Test
    public void register() throws Exception {
        int result = userService.register("13712@qq.com","hao");
        assertEquals(-1,result);
        System.out.println(result);
    }

}