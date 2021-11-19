package main.RBAC.servlet;

import main.RBAC.bean.User;
import main.RBAC.service.UserService;
import main.RBAC.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


public class LoginServletRBAC extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet被访问了doPost");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 调用userService.login()登录处理业务
        Map loginUser = userService.login(new User(null,username,password,null,1));
        if(loginUser.get("status").equals(10200)){
            //登陆成功，跳转到登录成功页面
            //保存用户登录之后的信息到session域中
            req.getSession().setAttribute("user", loginUser.get("name"));
            System.out.println("登录成功");
            req.getRequestDispatcher("web/pages/RBACPages/LoginSuccess.html").forward(req, resp);
        }else {
            System.out.println("用户名或密码错误，登录失败");
            req.getRequestDispatcher("web/pages/RBACPages/Login.html").forward(req,resp);
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
