package com.bean;

import java.util.Date;

public class Comment {
    private Integer commentId;
    private Integer commentUser; //评论人账号
    private String commentTest; //评论内容
    private Integer pid; //引用评论
    private Date createdTime; //上传时间
    private Integer status;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(Integer commentUser) {
        this.commentUser = commentUser;
    }

    public String getCommentTest() {
        return commentTest;
    }

    public void setCommentTest(String commentTest) {
        this.commentTest = commentTest;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Comment() {
    }

    public Comment(Integer commentId, Integer commentUser, String commentTest, Integer pid, Date createdTime, Integer status) {
        this.commentId = commentId;
        this.commentUser = commentUser;
        this.commentTest = commentTest;
        this.pid = pid;
        this.createdTime = createdTime;
        this.status = status;
    }
}
