package com.dao;

import com.bean.User;

import java.util.List;
import java.util.Map;

public interface UserDAO {

    //根据用户名查用户信息，若无则返回Null
    public Map queryUserByUsername(String username);

    //根据用户名和密码查用户信息，若无则说明用户名和密码之一有问题，顺便查出用户属性
    public Map queryUserByUsernameAndPassword(String username, String password);

    //保存用户信息
    public boolean saveUser(User user);

    // 通过用户名查询用户信息
    public User findUserByName(String username);

    //查询粉丝数
    public Integer queryFollowerAmount(Integer id);

    //查询关注数
    public Integer queryFollowedAmount(Integer id);

    // 查询粉丝列表
    public List<User> queryFollowerList(Integer id);

    //查询关注列表
    public List<User> queryFollowedList(Integer id);

    //增加关注
    public boolean follow(Integer id, Integer followId);

    //取消关注
    public boolean unfollow(Integer id, Integer followId);

    // 检查是否已关注
    public Map checkFollow(Integer id, Integer followId);

}

