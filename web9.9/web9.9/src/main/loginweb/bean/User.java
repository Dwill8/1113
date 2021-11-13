package main.loginweb.bean;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String gender;
    private String classId;
    private String courseId;
    private Integer courseMark;
    private String updatedTime;
    private String status;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getClassId() {
        return classId;
    }

    public String getCourseId() {
        return courseId;
    }

    public Integer getCourseMark() {
        return courseMark;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setCourseMark(int courseMark) {
        this.courseMark = courseMark;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", classId='" + classId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", courseMark=" + courseMark +
                '}';
    }

    public User(){

    }

    public User(Integer id, String username, String password, String name, String gender, String classId, String courseId, Integer courseMark, String updatedTime, String status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.classId = classId;
        this.courseId = courseId;
        this.courseMark = courseMark;
        this.updatedTime = updatedTime;
        this.status = status;
    }
}



