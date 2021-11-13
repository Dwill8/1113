package main.RBAC.bean;

public class RUser {
    private Integer id;
    private String username;
    private String password;
    private String updatedTime;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public Integer getStatus() {
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

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + ", update time=" + updatedTime +
                '}';
    }

    public RUser(){

    }

    public RUser(Integer id, String username, String password, String updatedTime, Integer status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.updatedTime = updatedTime;
        this.status = status;
    }
}
