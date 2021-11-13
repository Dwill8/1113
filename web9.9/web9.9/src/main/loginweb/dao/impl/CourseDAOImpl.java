package main.loginweb.dao.impl;

import main.loginweb.bean.Course;
import main.loginweb.dao.CourseDAO;
import main.loginweb.utils.JDBCUtils;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseDAOImpl implements CourseDAO {

    Connection conn = JDBCUtils.getConn();

    // 数据库连接对象
    PreparedStatement pstmt;

    @Override
    public List<Course> findByPage(String name, int pageNum, int pageSize) {

//        try {
//            pstmt = conn.prepareStatement("SELECT sc.student_id, sc.`name`, sc.course_name, sc.course_mark, teacher.teacher_name FROM (SELECT scr.student_id, scr.`name`, course.course_name, scr.course_mark, course.teacher_id FROM (SELECT student.student_id, student.`name`,s_c_relation.course_id, s_c_relation.course_mark FROM student LEFT JOIN s_c_relation ON student.student_id=s_c_relation.student_id WHERE student.username=?) AS scr LEFT JOIN course ON course.course_id = scr.course_id) AS sc LEFT JOIN teacher ON sc.teacher_id=teacher.teacher_id limit ? offset ?");
//            pstmt.setInt(2, pageSize);
//            // 设置SQL语句参数
//            pstmt.setInt(3, (pageNum - 1) * pageSize);
//            // 设置SQL语句参数
//            pstmt.setInt(1,name);
//            ResultSet rs = pstmt.executeQuery();
//            // 执行查询，返回结果集
//            List<Course> list = new ArrayList<Course>();
//            //新建列表储存每页信息
//            while (rs.next()) {
//                // 通过JavaBean保存值
//                Course course = new Course();
//                course.setStudentID(rs.getInt(1));
//                course.setStudentName(rs.getNString(2));
//                course.setCourseName(rs.getString(3));
//                course.setCourseTeacher(rs.getString(5));
//                course.setCourseMark(rs.getInt(4));
//                list.add(course); // 用户对象储存进列表
//            }
//            return list;
//            // 返回每页用户列表
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
        return null;
    }

    @Override
    public boolean saveCourse(Course course){
        return update(course.getStudentID(), course.getCourseID());
    }

    public boolean update(int studentID, int courseID) {
        try {
            pstmt = conn.prepareStatement("insert into s_c_relation('student_id', 'course_id') values(?,?)");//新选课对应关系插入关系表中
            pstmt.setInt(1, studentID);
            pstmt.setInt(2, courseID);
            pstmt.executeUpdate(); // 返回数据库储存成功否
            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Map queryByCourseID(int studentID, int courseID) {
        return checkCourse(studentID, courseID);
    }

    public Map checkCourse(int studentID, int courseID) {
        Map result = new HashMap();
        // 验证课程是否已选
        try {
            pstmt = conn.prepareStatement("select course_id from s_c_relation where student_id=?");
            // 设置SQL语句参数
            pstmt.setInt(1, studentID);
            ResultSet rs = pstmt.executeQuery();
            // 执行查询，返回结果集
            while (rs.next()) {
                // 通过JavaBean保存值

                if(rs.getInt(1) == courseID) {
                    result.put("course", new Course());
                    result.put("status", 10400);

                }
            }
            Course course = new Course();
            course.setCourseID(courseID);
            result.put("course", course);
            result.put("status", 10200);


        } catch (Exception e) {
            e.printStackTrace();
            result.put("course", new Course());
            result.put("status", 10500);
        }
        return result;
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from course";
        return null;
    }

    @Override
    public List<Course> queryForPageItems(int pageNum, int pageSize) {
        try {
            pstmt = conn.prepareStatement("SELECT sc.student_id, sc.`name`, sc.course_name, sc.course_mark, teacher.teacher_name FROM (SELECT scr.student_id, scr.`name`, course.course_name, scr.course_mark, course.teacher_id FROM (SELECT student.student_id, student.`name`,s_c_relation.course_id, s_c_relation.course_mark FROM student LEFT JOIN s_c_relation ON student.student_id=s_c_relation.student_id WHERE student.username=?) AS scr LEFT JOIN course ON course.course_id = scr.course_id) AS sc LEFT JOIN teacher ON sc.teacher_id=teacher.teacher_id limit ? offset ?");
            pstmt.setInt(2, pageSize);
            // 设置SQL语句参数
            pstmt.setInt(3, (pageNum - 1) * pageSize);
            // 设置SQL语句参数
//            HttpSession session = ServletActionContext.getRequest().getSession();
//            String name = (String) session.getAttribute("user");
            String name = "Ann";
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            // 执行查询，返回结果集
            List<Course> list = new ArrayList<Course>();
            //新建列表储存每页信息
            while (rs.next()) {
                // 通过JavaBean保存值
                Course course = new Course();
                course.setStudentID(rs.getInt(1));
                course.setStudentName(rs.getNString(2));
                course.setCourseName(rs.getString(3));
                course.setCourseTeacher(rs.getString(5));
                course.setCourseMark(rs.getInt(4));
                list.add(course); // 用户对象储存进列表
            }
            return list;
            // 返回每页课程列表
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
