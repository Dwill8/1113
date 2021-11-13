package main.loginweb.web;

import main.loginweb.bean.Course;
import main.loginweb.bean.Page;
import main.loginweb.bean.User;
import main.loginweb.service.CourseService;
import main.loginweb.service.impl.CourseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CourseServlet extends HttpServlet {
    private CourseService courseService = new CourseServiceImpl();

    // 处理分页功能
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1.获取参数pageNo和pageSize(这里为默认或传递)
        int pageNum = 1;
        pageNum = Integer.parseInt(req.getParameter("pageNum"));
        int pageSize = Page.PAGE_SIZE;
        pageSize = Integer.parseInt(req.getParameter("pageSize"));

        // 2.调用CourseService.page(pageNo,pageSize):Course对象
        Page page = courseService.pageCourse(pageNum, pageSize);

        // 3.保存page对象到Request域中
        req.setAttribute("page",page);

        // 4.请求转发到某某页面
        req.getRequestDispatcher("/pages/CourseCheck.html").forward(req,resp);
        pageNum++;


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentID = req.getIntHeader("studentID");
        int courseID = req.getIntHeader("courseID");

        if(courseService.existCourse(studentID, courseID)){ //不可用
            req.getRequestDispatcher("web/pages/AddCourse.html").forward(req,resp); //跳回选课页面

        }else { //可用
            System.out.println("课程可选，保存至数据库");
            //调用UserService保存到数据库
            courseService.addCourse(new Course(studentID, courseID, null, null, null, -1));
            //跳到注册成功页面
            req.getRequestDispatcher("web/pages/AddCourseSuccess.html").forward(req, resp);
        }
    }
}
