package org.mf.startupadpage.entity;

public class BnfStartupAdPageDto {


    /**
     * id : 4
     * title : 测试广告
     * picUrl : http://watermelon-pic-test.b0.upaiyun.com/StartupAdPage/201511301457574441936.png
     * relatedUrl : http://www.baidu.com
     * addTime : 2015-11-30T14:57:58
     * updateTime : 2015-11-30T15:57:39
     * publisher : admin
     * publishTime : 2015-11-30T14:00:00
     * isDisplay : true
     * isDelete : false
     * displaySecond : 3
     */

    private int id;
    private String title;
    private String picUrl;
    private String relatedUrl;
    private String addTime;
    private String updateTime;
    private String publisher;
    private String publishTime;
    private boolean isDisplay;
    private boolean isDelete;
    private int displaySecond;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setRelatedUrl(String relatedUrl) {
        this.relatedUrl = relatedUrl;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public void setIsDisplay(boolean isDisplay) {
        this.isDisplay = isDisplay;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public void setDisplaySecond(int displaySecond) {
        this.displaySecond = displaySecond;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getRelatedUrl() {
        return relatedUrl;
    }

    public String getAddTime() {
        return addTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public boolean isIsDisplay() {
        return isDisplay;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public int getDisplaySecond() {
        return displaySecond;
    }
}