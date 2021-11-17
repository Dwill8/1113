package main.loginweb.service.impl;

import main.loginweb.bean.Page;
import main.loginweb.bean.User;
import main.loginweb.dao.UserDAO;
import main.loginweb.dao.impl.UserDaoImpl;
import main.loginweb.service.UserService;

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
    @Override
    public Page pageUser(int pageNum, int pageSize){
        userDAO.findByPage(pageNum, pageSize); //储存上一页和下一页的页数，总信息数
        Page page = new Page();
        //设置当前页码
        page.setPageNum(pageNum);
        //设置每页显示数量
        page.setPageSize(pageSize);
        return page;
    }


}
