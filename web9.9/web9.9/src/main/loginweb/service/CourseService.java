package main.loginweb.service;

import main.loginweb.bean.Course;
import main.loginweb.bean.Page;

public interface CourseService {
    public void addCourse(Course course);
    public boolean existCourse(int studentID, int courseID);
    public Page pageCourse(int pageNum, int pageSize);
}
