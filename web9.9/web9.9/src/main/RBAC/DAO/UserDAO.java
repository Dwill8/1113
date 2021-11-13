package main.RBAC.DAO;

import main.RBAC.bean.User;

import java.util.List;
import java.util.Map;

public interface UserDAO {

    //根据用户名查用户信息，若无则返回Null
    public Map queryUserByUsername(String username);
    //根据用户名和密码查用户信息，若无则说明用户名和密码之一有问题
    public Map queryUserByUsernameAndPassword(String username, String password);
    //保存用户信息
    public boolean saveUser(User user);
}

