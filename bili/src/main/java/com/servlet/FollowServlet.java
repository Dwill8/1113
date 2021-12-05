package com.servlet;

import com.bean.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class FollowServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    // private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("1");
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("2");
        //获取对应的请求参数
        String method = request.getParameter("method");
        //根据请求参数去调用对应的方法。
        if ("follow".equals(method)) {
            follow(request, response);
        } else if ("unfollow".equals(method)) {
            unfollow(request, response);
        } else if ("queryFollowerList".equals(method)) {
            queryFollowerList(request, response);
        } else if ("queryFollowerList".equals(method)) {
            queryFollowedList(request,response);
        } else if ("queryFollowerAmount".equals(method)) {
            queryFollowerAmount(request, response);
        } else if ("queryFollowedAmount".equals(method)) {
            queryFollowedAmount(request, response);
        }
    }

    protected void follow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("3");
        // 获取session里的username
        System.out.println((Integer)req.getSession().getAttribute("user_id"));
        Map map = userService.follow((Integer) req.getSession().getAttribute("user_id"), Integer.valueOf(req.getParameter("follow_id")));
        if (map.get("status").equals(34200)) {
            System.out.println("关注成功");
        } else if (map.get("status").equals(34401)) {
            System.out.println("已关注");
        } else {
            System.out.println("关注失败");
        }
    }

    protected void unfollow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取session里的username
        Map map = userService.unfollow((Integer)req.getSession().getAttribute("user_id"), Integer.valueOf(req.getParameter("follow_id")));
        if(map.get("status").equals(34200)) {
            System.out.println("取关成功");
        } else if(map.get("status").equals(34401)) {
            System.out.println("未关注");
        } else {
            System.out.println("取关失败");
        }
    }

    protected void queryFollowerList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("粉丝名单servlet");
        List<User> list = userService.queryFollowerList((Integer)req.getSession().getAttribute("user_id"));
        req.setAttribute("list", list);
    }

    protected void queryFollowedList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("关注名单servlet");
        List<User> list = userService.queryFollowedList((Integer)req.getSession().getAttribute("user_id"));
        req.setAttribute("list", list);
    }

    protected void queryFollowerAmount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map map = userService.queryFollowerAmount((Integer)req.getSession().getAttribute("user_id"));
        if(map.get("status").equals(30200)) {
            System.out.println("获取粉丝数成功");
            req.setAttribute("follower_amount", map.get("amount"));
            req.getSession().setAttribute("follower_amount", map.get("amount"));
        } else {
            System.out.println("获取粉丝数失败");
        }
    }

    protected void queryFollowedAmount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map map = userService.queryFollowedAmount((Integer)req.getSession().getAttribute("user_id"));
        if(map.get("status").equals(31200)) {
            System.out.println("获取关注数成功");
            req.setAttribute("followed_amount", map.get("amount"));
            req.getSession().setAttribute("followed_amount", map.get("amount"));
        } else {
            System.out.println("获取关注数失败");
        }
    }
}
