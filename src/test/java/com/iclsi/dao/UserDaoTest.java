package com.iclsi.dao;

import com.iclsi.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * 配置spring和Junit整合，Junit启动时加载springIOC容器
 * spring-test，Junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉Junit spring配置文件
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void findById() throws Exception {
        long id = 1;
        User user = userDao.findById(id);
        System.out.println(user.getName());
    }

    @Test
    public void insert() throws Exception {
        User user = new User(3, "lu", "mingming", "12345678910", "123@qq.com");
        userDao.insert(user);
    }

    @Test
    public void queryUserByEmail() throws Exception {
        String email = "12345@qq.com";
        User user = userDao.queryUserByEmail(email);
        System.out.println(user);
    }

}