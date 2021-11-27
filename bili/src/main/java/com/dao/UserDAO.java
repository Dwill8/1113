package com.dao;

import com.bean.User;

import java.util.Map;

public interface UserDAO {

    //根据用户名查用户信息，若无则返回Null
    public Map queryUserByUsername(String username);
    //根据用户名和密码查用户信息，若无则说明用户名和密码之一有问题
    public Map queryUserByUsernameAndPassword(String username, String password);
    //保存用户信息
    public boolean saveUser(User user);

    //查询粉丝数
    public Integer queryFollowerAmount(int id);

    //查询关注数
    public Integer queryFollowedAmount(int id);

    //增加关注
    public Integer follow(int id, int followId);

    //取消关注
    public Integer unfollow(int id, int followId);

}

