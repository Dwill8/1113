package main.loginweb.bean;

import java.util.List;

public class Page {
    public static final Integer PAGE_SIZE = 3;
    //要不要加默认页码为1

    private Integer pageNum; //当前页
    private Integer pageTotal; //总页码
    private Integer pageSize = PAGE_SIZE; //每页条数
    private Integer userTotalCount; //总记录数
    private List<Course> list;//当前页数据
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getUserTotalCount() {
        return userTotalCount;
    }

    public void setCourseTotalCount(Integer userTotalCount) {
        this.userTotalCount = userTotalCount;
    }

    public List<Course> getList() {
        return list;
    }

    public void setList(List<Course> list) {
        this.list = list;
    }

    public Page(Integer pageNum, Integer pageTotal, Integer pageSize, Integer userTotalCount, List<Course> list, String username) {
        this.pageNum = pageNum;
        this.pageTotal = pageTotal;
        this.pageSize = pageSize;
        this.userTotalCount = userTotalCount;
        this.list = list;
        this.username = username;
    }

    public Page(){}
}
