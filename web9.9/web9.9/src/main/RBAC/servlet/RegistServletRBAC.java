package main.RBAC.servlet;

import main.RBAC.bean.User;
import main.RBAC.service.UserService;
import main.RBAC.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServletRBAC extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RegistServlet被访问了");
        //获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //检查用户名是否可用
        if(userService.existUsername(username)){ //不可用
            System.out.println("用户名[" + username +"]已存在");
            req.getRequestDispatcher("web/pages/RBACPages/Regist.html").forward(req,resp); //跳回注册页面

        }else{ //可用
            System.out.println("用户名可用，保存至数据库");
            //调用UserService保存到数据库
            userService.registerUser(new User(null,username,password,null,1));
            //跳到注册成功页面
            req.getRequestDispatcher("web/pages/RBACPages/RegistSuccess.html").forward(req,resp);
        }

    }
}
