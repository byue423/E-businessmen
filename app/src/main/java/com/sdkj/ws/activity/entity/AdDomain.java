package com.sdkj.ws.activity.entity;

/**
 * AD广告的实体
 */
public class AdDomain {
	private String id; // ���id
	private String date; // ����日期
	private String title; // ����标题
	private String topicFrom; //  标题�来源����
	private String topic; //       热搜 标题
	private String imgUrl; // 图片链接
	private boolean isAd; // �是否为广告��
	private String startTime; // �开始时间�
	private String endTime; // 结束时间
	private String targetUrl; // 转至哪里
	private int width; // ��宽度
	private int height; // �高度�
	private boolean available; // �显示��
	private String top_desc;// �图片描述�
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public boolean isAd() {
		return isAd;
	}

	public void setAd(boolean isAd) {
		this.isAd = isAd;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTopicFrom() {
		return topicFrom;
	}

	public void setTopicFrom(String topicFrom) {
		this.topicFrom = topicFrom;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}


	public boolean isAvailable() {
		return available;
	}

	public String getTop_desc() {
		return top_desc;
	}

	public void setTop_desc(String top_desc) {
		this.top_desc = top_desc;
	}

	public void setAvailable(boolean available) {
		this.available = available;

	}

}
