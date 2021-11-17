package main.RBAC.bean;

public class Role {
    Integer rid;
    String name;
    String updatedTime;
    Integer status;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Role(Integer rid, String name, String updatedTime, Integer status) {
        this.rid = rid;
        this.name = name;
        this.updatedTime = updatedTime;
        this.status = status;
    }
    public Role() {}
}
