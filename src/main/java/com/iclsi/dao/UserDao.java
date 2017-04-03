package com.iclsi.dao;

import com.iclsi.entity.User;

import java.util.List;

/**
 * Created by luhaoming123 on 2016/12/22.
 */
public interface UserDao {

    /**
     * 根据ID查询用户信息
     * @param id
     * @return
     */
    public String findById(long id);

    /**
     * 根据用户名称name查询用户信息
     * @param name
     * @return 拥有同名的用户用List
     */
    public List<User> findByName(String name);

    /**
     * 添加用户
     * @param user
     */
    public void insert(User user);

    /**
     * 根据ID删除用户
     * @param id
     */
    public void delete(long id);

    /**
     * 更新用户
     * @param user
     */
    public void update(User user);

}
