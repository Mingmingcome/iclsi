package com.iclsi.service;

import com.iclsi.dao.ClockDao;
import com.iclsi.entity.Clock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

/**
 * Created by luhaoming123 on 2017/5/1.
 */
@Service
public class ClockService {

    @Autowired
    private ClockDao clockDao;

    private final String salt = "1234567890!@#$%^&*()QWER";

    /**
     * 根据clockId查询该clock是否被注册过
     *
     * @param clockId
     * @return 1:clockId已经被注册过 0:clockId没有被注册过
     */
    public int queryClockByClockId(long clockId) {
        if(clockDao.findById(clockId) == null) {
            return 0;
        }
        return 1;
    }

    /**
     *
     * @param clockId
     * @param password
     * @return -1:clock已经被注册过 0:clock注册不成功 1:clock注册成功
     */
    @Transactional
    public int register(long clockId, String password) {
        if(queryClockByClockId(clockId) == 0) {
            password = getMD5(password);
            Clock clock = new Clock(clockId, password);
            clockDao.insert(clock);
        }else {
            return -1;
        }
        return queryClockByClockId(clockId);
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
