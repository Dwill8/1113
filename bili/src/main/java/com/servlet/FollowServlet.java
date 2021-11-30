package com.servlet;

import com.bean.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FollowServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    protected void follow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取session里的username
        Map map = userService.follow((Integer)req.getSession().getAttribute("user_id"), Integer.valueOf(req.getParameter("follow_id")));
        if(map.get("status").equals(34200)) {
            System.out.println("关注成功");
        } else if(map.get("status").equals(34401)) {
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
        List<User> list = userService.queryFollowerList((Integer)req.getSession().getAttribute("user_id"));
        req.setAttribute("list", list);
    }

    protected void queryFollowedList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> list = userService.queryFollowedList((Integer)req.getSession().getAttribute("user_id"));
        req.setAttribute("list", list);
    }

    protected void queryFollowerAmount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map map = userService.queryFollowerAmount((Integer)req.getSession().getAttribute("user_id"));
        if(map.get("status").equals(30200)) {
            System.out.println("获取成功");
            req.setAttribute("follower_amount", map.get("amount"));
            req.getSession().setAttribute("follower_amount", map.get("amount"));
        } else {
            System.out.println("获取失败");
        }
    }

    protected void queryFollowedAmount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map map = userService.queryFollowedAmount((Integer)req.getSession().getAttribute("user_id"));
        if(map.get("status").equals(31200)) {
            System.out.println("获取成功");
            req.setAttribute("followed_amount", map.get("amount"));
            req.getSession().setAttribute("followed_amount", map.get("amount"));
        } else {
            System.out.println("获取失败");
        }
    }
}
