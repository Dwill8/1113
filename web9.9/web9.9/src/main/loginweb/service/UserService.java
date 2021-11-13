package main.loginweb.service;
import main.loginweb.bean.Page;
import main.loginweb.bean.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    //注册
    public void registerUser(User user);

    //登录
    public Map login(User user);

    //检查用户名是否可用
    public boolean existUsername(String username);

    public Page pageUser(int pageNum, int pageSize);
}
