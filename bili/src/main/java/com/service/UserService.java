package com.service;

import com.bean.User;

import java.util.Map;

public interface UserService {
    //注册
    public void registerUser(User user);

    //登录
    public Map login(User user);

    //检查用户名是否可用
    public boolean existUsername(String username);

    //查询粉丝数
    public int queryFollowerAmount(int id);

    //查询关注数
    public int queryFollowedAmount(int id);

    //增加关注
    public int follow(int id, int followId);

    //取消关注
    public int unfollow(int id, int followId);
}
