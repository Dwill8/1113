package com.service.impl;

import com.bean.User;
import com.dao.UserDAO;
import com.dao.impl.UserDAOImpl;
import com.service.UserService;

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
    public int queryFollowerAmount(int id) {
        return 0;
    }

    @Override
    public int queryFollowedAmount(int id) {
        return 0;
    }

    @Override
    public int follow(int id, int followId) {
        return 0;
    }

    @Override
    public int unfollow(int id, int followId) {
        return 0;
    }
}
