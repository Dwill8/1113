package com.service.impl;

import com.bean.User;
import com.dao.UserDAO;
import com.dao.impl.UserDAOImpl;
import com.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public void registerUser(User user) {
        userDAO.saveUser(user);
        System.out.println("registUser被调用，用户名：" + user.getUsername());

    }

    @Override
    public Map login(User user) {
        return userDAO.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        if (userDAO.queryUserByUsername(username).get("status").equals(20400)) {
            return false;
        } else {
            System.out.println("该用户名已被注册，请更换用户名");
            return true;
        }
    }

    @Override
    public Map queryFollowerAmount(Integer id) {
        Map result = new HashMap();
        if(userDAO.queryFollowerAmount(id) == null) {
            result.put("amount", null);
            result.put("status", 30400);
        } else {
            result.put("amount", userDAO.queryFollowerAmount(id));
            result.put("status", 30200);
        }
        return result;
    }

    @Override
    public Map queryFollowedAmount(Integer id) {
        Map result = new HashMap();
        if(userDAO.queryFollowedAmount(id) == null) {
            result.put("amount", null);
            result.put("status", 31400);
        } else {
            result.put("amount", userDAO.queryFollowedAmount(id));
            result.put("status", 31200);
        }
        return result;
    }

    @Override
    public Map queryFollowerList(Integer id) {
        Map result = new HashMap();
        if(userDAO.queryFollowerList(id) == null) { //问题：此处粉丝为0列表是null的情况怎么办
            result.put("list", null);
            result.put("status", 32400);
        } else {
            result.put("list", userDAO.queryFollowerList(id));
            result.put("status", 32200);
        }
        return result;
    }

    @Override
    public Map queryFollowedList(Integer id) {
        Map result = new HashMap();
        if(userDAO.queryFollowedList(id) == null) { //问题：此处粉丝为0列表是null的情况怎么办
            result.put("list", null);
            result.put("status", 33400);
        } else {
            result.put("list", userDAO.queryFollowedList(id));
            result.put("status", 33200);
        }
        return result;
    }

    @Override
    public Map follow(Integer id, Integer followId) {
        Map result = new HashMap();
        if(!userDAO.follow(id, followId)){
            result.put("follow_id", null);
            result.put("status", 34400);
        } else {
            result.put("follow_id", followId);
            result.put("status", 34200);
        }
        return result;
    }

    @Override
    public Map unfollow(Integer id, Integer unfollowId) {
        Map result = new HashMap();
        if(!userDAO.follow(id, unfollowId)){
            result.put("unfollow_id", null);
            result.put("status", 35400);
        } else {
            result.put("unfollow_id", unfollowId);
            result.put("status", 35200);
        }
        return result;
    }
}
