package com.szqj.service.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "service_meet_plan")
public class MeetPlan {
	 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String meetPlanId;
	
	private String meetPlanName;//日程名称
	
	private String startDate;//开始时间
   
    private String teacherName;//教师名称
    
    private String teacherCompany;//教师所在单位
    
    private String teacherPost;//教师职称
    
    private String meetId;//会议ID
    
    
    private Date createDate;//创建时间

	public String getMeetPlanId() {
		return meetPlanId;
	}

	public void setMeetPlanId(String meetPlanId) {
		this.meetPlanId = meetPlanId;
	}

	public String getMeetPlanName() {
		return meetPlanName;
	}

	public void setMeetPlanName(String meetPlanName) {
		this.meetPlanName = meetPlanName;
	}

	

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherCompany() {
		return teacherCompany;
	}

	public void setTeacherCompany(String teacherCompany) {
		this.teacherCompany = teacherCompany;
	}

	public String getTeacherPost() {
		return teacherPost;
	}

	public void setTeacherPost(String teacherPost) {
		this.teacherPost = teacherPost;
	}

	public String getMeetId() {
		return meetId;
	}

	public void setMeetId(String meetId) {
		this.meetId = meetId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
	
	
    

}
