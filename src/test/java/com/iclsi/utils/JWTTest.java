package com.iclsi.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by luhaoming123 on 2017/5/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-util.xml")
public class JWTTest {

    @Test
    public void createJWT() throws Exception {
        JWT jwt = new JWT();
        String tmp = jwt.createJWT("1", "luhaoming", "all", 3600000);
        System.out.println(tmp);
        jwt.parseJWT(tmp);
    }

    @Test
    public void parseJWT() throws Exception {

    }

}