package com.servlet;

import com.service.UserService;
import com.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class FollowServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    protected void follow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map
        userService.follow(req.getSession().getAttribute("user_id"), req.getParameter("follow_id"));
    }
}
