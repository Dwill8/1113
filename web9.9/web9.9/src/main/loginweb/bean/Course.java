package main.loginweb.bean;

public class Course {
    private int studentID;
    private int courseID;
    private String studentName;
    private String courseName;
    private String courseTeacher;
    private int courseMark;

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public int getCourseMark() {
        return courseMark;
    }

    public void setCourseMark(int courseMark) {
        this.courseMark = courseMark;
    }

    public Course() {

    }

    public Course(int studentID, int courseID, String studentName, String courseName, String courseTeacher, int courseMark) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.studentName = studentName;
        this.courseName = courseName;
        this.courseTeacher = courseTeacher;
        this.courseMark = courseMark;
    }
}
