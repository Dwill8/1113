package main.RBAC.service.impl;

import main.RBAC.bean.User;
import main.RBAC.DAO.UserDAO;
import main.RBAC.DAO.impl.UserDaoImpl;
import main.RBAC.service.UserService;

import java.util.Map;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        userDAO.saveUser(user);
        System.out.println("registUser被调用，用户名：" + user.getUsername());

    }

    @Override
    public Map login(User user) {
        return userDAO.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        if(userDAO.queryUserByUsername(username).get("status").equals(20400)){
            return false;
        }else {
            System.out.println("该用户名已被注册，请更换用户名");
            return true;
        }
    }
}
