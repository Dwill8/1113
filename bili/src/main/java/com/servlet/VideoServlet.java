package com.servlet;

import com.service.UserService;
import com.service.VideoService;
import com.service.impl.UserServiceImpl;
import com.service.impl.VideoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class VideoServlet extends HttpServlet {
    private VideoService videoService = new VideoServiceImpl();

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //获取对应的请求参数
        String method = req.getParameter("method");
        //根据请求参数去调用对应的方法。
        if ("rewardCoin".equals(method)) {
            rewardCoin(req, resp);
        } else if ("like".equals(method)) {
            like(req, resp);
        }
    }

    public void rewardCoin(HttpServletRequest req, HttpServletResponse resp) {
        Map map = videoService.rewardCoin((Integer) req.getSession().getAttribute("user_id"), Integer.valueOf(req.getParameter("vid")));
        System.out.println("user_id:" + (Integer)req.getSession().getAttribute("user_id") + ", vid:" + req.getParameter("vid"));
        if (map.get("status").equals(34200)) {
            System.out.println("投币成功");
        } else if (map.get("status").equals(34401)) {
            System.out.println("已投币");
        } else {
            System.out.println("投币失败");
        }
    }

    public void like(HttpServletRequest request, HttpServletResponse response) {

    }
}
