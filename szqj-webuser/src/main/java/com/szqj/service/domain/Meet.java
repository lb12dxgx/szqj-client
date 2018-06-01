package com.szqj.service.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "meet")
public class Meet {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String meetId;
	
	private String meetName;//会议名称
	
	private String meetPlace;//会议地点
	
	private Integer meetPrice;//会议价格
	
	private Integer num;//总人数
	
	private Date startDate;//开始时间
	
	private Date endDate;//结束时间
	
	private String meetSummary;//会议摘要
	 
	private String meetContent;//会议内容
	
	private String meetPicId;//会议宣传图片
	
	private Date createDate;//创建时间
	
	private Integer isSign=0;//开启报名 0:不允许签到  1:允许签到
	

	public String getMeetId() {
		return meetId;
	}

	public void setMeetId(String meetId) {
		this.meetId = meetId;
	}

	public String getMeetName() {
		return meetName;
	}

	public void setMeetName(String meetName) {
		this.meetName = meetName;
	}

	public String getMeetSummary() {
		return meetSummary;
	}

	public void setMeetSummary(String meetSummary) {
		this.meetSummary = meetSummary;
	}

	public String getMeetContent() {
		return meetContent;
	}

	public void setMeetContent(String meetContent) {
		this.meetContent = meetContent;
	}

	public String getMeetPlace() {
		return meetPlace;
	}

	public void setMeetPlace(String meetPlace) {
		this.meetPlace = meetPlace;
	}

	public Integer getMeetPrice() {
		return meetPrice;
	}

	public void setMeetPrice(Integer meetPrice) {
		this.meetPrice = meetPrice;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getMeetPicId() {
		return meetPicId;
	}

	public void setMeetPicId(String meetPicId) {
		this.meetPicId = meetPicId;
	}

	public Integer getIsSign() {
		return isSign;
	}

	public void setIsSign(Integer isSign) {
		this.isSign = isSign;
	}
	
	
	
}
