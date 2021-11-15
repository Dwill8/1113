package main.loginweb.service.impl;

import main.loginweb.bean.Course;
import main.loginweb.bean.Page;
import main.loginweb.dao.CourseDAO;
import main.loginweb.dao.UserDAO;
import main.loginweb.dao.impl.CourseDAOImpl;
import main.loginweb.dao.impl.UserDaoImpl;
import main.loginweb.service.CourseService;

import java.util.List;


public class CourseServiceImpl implements CourseService {

    private CourseDAO courseDAO = new CourseDAOImpl();

    @Override
    public void addCourse(Course course) {
        courseDAO.saveCourse(course);
    }

    @Override
    public boolean existCourse(int studentID, int courseID) {
        if (courseDAO.queryByCourseID(studentID, courseID).get("status").equals(10200)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Page pageCourse(int pageNum, int pageSize) {
        String username = null;

        Page page = new Page();
        //设置当前页码
        page.setPageNum(pageNum);
        //设置每页显示数量
        page.setPageSize(pageSize);
        //求总记录数
        Integer pageTotalCount = courseDAO.queryForPageTotalCount();
        //设置总记录数
        page.setCourseTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        //设置总页码
        page.setPageTotal(pageTotal);

        //求当前页数据
        List<Course> list = courseDAO.queryForPageItems(pageNum, pageSize);
        //设置当前页数据
        page.setList(list);
        return page;
    }
}
