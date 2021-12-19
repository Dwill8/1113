package com.bean;

import java.util.Date;
import java.util.List;

public class Video {
    private Integer videoId;
    private String videoName; //视频名：时间戳+用户id
    private String videoNameOrigin; //原文件名
    private String videoURL; //视频地址
    private String videoTitle; //视频标题
    private String videoPortrait; //视频封面图
    private String videoPortraitSmall; //视频封面缩略图
    private List<String> bulletScreen; //弹幕
    private Integer userId; //账号
    private Integer videoLikedNum; //点赞数
    private Integer videoForwardNum; //转发数
    private Integer coinNum; //投币数
    private Date createdTime; //上传时间
    private Integer status;

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoNameOrigin() {
        return videoNameOrigin;
    }

    public void setVideoNameOrigin(String videoNameOrigin) {
        this.videoNameOrigin = videoNameOrigin;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoPortrait() {
        return videoPortrait;
    }

    public void setVideoPortrait(String videoPortrait) {
        this.videoPortrait = videoPortrait;
    }

    public String getVideoPortraitSmall() {
        return videoPortraitSmall;
    }

    public void setVideoPortraitSmall(String videoPortraitSmall) {
        this.videoPortraitSmall = videoPortraitSmall;
    }

    public List<String> getBulletScreen() {
        return bulletScreen;
    }

    public void setBulletScreen(List<String> bulletScreen) {
        this.bulletScreen = bulletScreen;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVideoLikedNum() {
        return videoLikedNum;
    }

    public void setVideoLikedNum(Integer videoLikedNum) {
        this.videoLikedNum = videoLikedNum;
    }

    public Integer getVideoForwardNum() {
        return videoForwardNum;
    }

    public void setVideoForwardNum(Integer videoForwardNum) {
        this.videoForwardNum = videoForwardNum;
    }

    public Integer getCoinNum() {
        return coinNum;
    }

    public void setCoinNum(Integer coinNum) {
        this.coinNum = coinNum;
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

    @Override
    public String toString() {
        return "Video{" +
                "videoId=" + videoId +
                ", videoName='" + videoName + '\'' +
                ", videoNameOrigin='" + videoNameOrigin + '\'' +
                ", videoURL='" + videoURL + '\'' +
                ", videoTitle='" + videoTitle + '\'' +
                ", videoPortrait='" + videoPortrait + '\'' +
                ", videoPortraitSmall='" + videoPortraitSmall + '\'' +
                ", bulletScreen=" + bulletScreen +
                ", userId=" + userId +
                ", videoLikedNum=" + videoLikedNum +
                ", videoForwardNum=" + videoForwardNum +
                ", coinNum=" + coinNum +
                ", createdTime=" + createdTime +
                ", status=" + status +
                '}';
    }

    public Video() {}

    public Video(Integer videoId, String videoName, String videoNameOrigin, String videoURL, String videoTitle, String videoPortrait, String videoPortraitSmall, List<String> bulletScreen, Integer userId, Integer videoLikedNum, Integer videoForwardNum, Integer coinNum, Date createdTime, Integer status) {
        this.videoId = videoId;
        this.videoName = videoName;
        this.videoNameOrigin = videoNameOrigin;
        this.videoURL = videoURL;
        this.videoTitle = videoTitle;
        this.videoPortrait = videoPortrait;
        this.videoPortraitSmall = videoPortraitSmall;
        this.bulletScreen = bulletScreen;
        this.userId = userId;
        this.videoLikedNum = videoLikedNum;
        this.videoForwardNum = videoForwardNum;
        this.coinNum = coinNum;
        this.createdTime = createdTime;
        this.status = status;
    }
}
