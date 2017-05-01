package com.iclsi.dao;

import com.iclsi.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by luhaoming123 on 2016/12/22.
 */
@Repository
public interface UserDao {

    /**
     * 根据ID查询用户信息
     * @param userId
     * @return
     */
    public User findById(long userId);

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

    /**
     * 根据email查询该email是否被注册过
     *
     * @param email
     * @return
     */
    public User queryUserByEmail(String email);
}
