package com.servlet;

import com.bean.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet被访问了doGet"); // log 4j,junit
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet被访问了doPost");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        // 调用userService.login()登录处理业务
        Map loginUser = userService.login(user);
        if(loginUser.get("status").equals(10200)){
            user = userService.findUserByName(username);
            //保存用户登录之后的信息到session域中
            HttpSession session = req.getSession();
            req.getSession().setAttribute("user_id", user.getId());
            System.out.println((Integer)req.getSession().getAttribute("user_id"));
            req.getSession().setAttribute("username", loginUser.get("username"));
            req.getSession().setAttribute("portrait", user.getPortrait());
            req.getSession().setAttribute("permission", user.getPermission());

            //登陆成功，跳转到登录成功页面
            System.out.println("登录成功");
            req.getRequestDispatcher("web/pages/LoginSuccess.html").forward(req, resp);
        }else {
            System.out.println("用户名或密码错误，登录失败");
            req.getRequestDispatcher("web/pages/Login.html").forward(req,resp);
        }
    }
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }

    protected String getSessionName(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException {
        return (String) req.getSession(true).getAttribute("user");
    }
}
