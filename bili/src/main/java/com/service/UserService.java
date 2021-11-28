package com.service;

import com.bean.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    //注册
    public void registerUser(User user);

    //登录
    public Map login(User user);

    //检查用户名是否可用
    public boolean existUsername(String username);

    //查询粉丝数
    public Map queryFollowerAmount(Integer id);

    //查询关注数
    public Map queryFollowedAmount(Integer id);

    // 查询粉丝列表
    public Map queryFollowerList(Integer id);

    //查询关注列表
    public Map queryFollowedList(Integer id);

    //增加关注
    public Map follow(Integer id, Integer followId);

    //取消关注
    public Map unfollow(Integer id, Integer unfollowId);
}
