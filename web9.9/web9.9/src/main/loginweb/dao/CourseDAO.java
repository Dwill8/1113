package main.loginweb.dao;

import java.util.List;
import java.util.Map;

import main.loginweb.bean.Course;



public interface CourseDAO {
    //分页显示
    public List<Course> findByPage(String name, int pageNum, int pageSize);
    //储存已选课程
    public boolean saveCourse(Course course);
    //检查课程是否已选
    public Map queryByCourseID(int studentID, int courseID);
    //课程
    public Integer queryForPageTotalCount();

    public List<Course> queryForPageItems(int pageNum, int pageSize);

}
