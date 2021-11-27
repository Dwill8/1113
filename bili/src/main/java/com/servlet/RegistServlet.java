package com.servlet;

import com.bean.Gender;
import com.bean.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RegistServlet被访问了");
        //获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");
        String email = req.getParameter("email");


        //检查用户名是否可用
        if(userService.existUsername(username)){ //不可用
            System.out.println("用户名[" + username +"]已存在");
            req.getRequestDispatcher("web/pages/Regist.html").forward(req,resp); //跳回注册页面

        }else{ //可用
            System.out.println("用户名可用，保存至数据库");
            //调用UserService保存到数据库
            userService.registerUser(new User(null,username,password,gender,email,null,null,null,null,0,0));
            //跳到注册成功页面
            req.getRequestDispatcher("web/pages/RegistSuccess.html").forward(req,resp);
        }

    }
}
