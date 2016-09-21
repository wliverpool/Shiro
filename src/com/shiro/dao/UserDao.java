package com.shiro.dao;

import java.util.List;

import com.shiro.pojo.User;

/**
 * 用户dao
 * @author 吴福明
 *
 */
public interface UserDao {

	public User createUser(User user);
    public User updateUser(User user);
    public void deleteUser(Long userId);

    User findOne(Long userId);

    List<User> findAll();

    User findByUsername(String username);
}