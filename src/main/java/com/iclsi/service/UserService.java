package com.iclsi.service;

import com.iclsi.dao.UserDao;
import com.iclsi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

/**
 * 有关用户的逻辑代码
 * Created by luhaoming123 on 2017/4/17.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    private final String salt = "1234567890!@#$%^&*()QWER";

    /**
     * 根据email查询该email是否被注册过
     *
     * @param email
     * @return 1:email已经被注册过 0:email没有被注册过
     */
    public int queryUserByEmail(String email) {
        if(userDao.queryUserByEmail(email) == null) {
            return 0;
        }
        return 1;
    }

    /**
     *
     * @param email
     * @param password
     * @return -1:email已经被注册过 0:注册不成功 1:注册成功
     */
    @Transactional
    public int register(String email, String password) {
        if(queryUserByEmail(email) == 0) {
            password = getMD5(password);
            User user = new User(email, password);
            userDao.insert(user);
        }else {
            return -1;
        }
        return queryUserByEmail(email);
    }

    /**
     *
     * @param origin 原始值
     * @return String 加密后的字符串
     */
    private String getMD5(String origin) {
        String base = origin + "/" + salt;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }
}
