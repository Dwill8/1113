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
    public User findUserByName(String username) {
        return userDAO.findUserByName(username);
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
    public List<User> queryFollowerList(Integer id) {
        return userDAO.queryFollowerList(id);
    }

    @Override
    public List<User> queryFollowedList(Integer id) {
        return userDAO.queryFollowedList(id);
    }

    @Override
    public Map follow(Integer id, Integer followId) {
        Map result = new HashMap();
        //检查是否已关注
        if(userDAO.checkFollow(id, followId).get("status").equals(0)) {
            //检查关注成功是否写入数据库
            if (!userDAO.follow(id, followId)) {
                // 未写入
                result.put("follow_id", null);
                result.put("status", 34400); //
            } else {
                // 写入
                result.put("follow_id", followId);
                result.put("status", 34200);
            }
        } else {
            // 已关注
            result.put("follow_id", followId);
            result.put("status", 34401);
        }
        return result;
    }

    @Override
    public Map unfollow(Integer id, Integer unfollowId) {
        Map result = new HashMap();
        //检查是否已关注
        if(userDAO.checkFollow(id, unfollowId).get("status").equals(1)) {
            if (!userDAO.unfollow(id, unfollowId)) {
                result.put("unfollow_id", null);
                result.put("status", 35400);
            } else {
                result.put("unfollow_id", unfollowId);
                result.put("status", 35200);
            }
        } else {
            // 未关注
            result.put("follow_id", unfollowId);
            result.put("status", 35401);
        }
        return result;
    }
}
