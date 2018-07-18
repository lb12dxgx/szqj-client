package com.szqj.service.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 学习经历
 * @author lb12
 * 
 */

@Entity
@Table(name = "service_per_study_info")
public class PerStudyInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String perStudyInfoId;//学习信息ID

	private String personId;//用户id
	
	private String schoole;//学校名称

	private String startDate;//开始时间
	
	private String endDate;//结束时间
	
	private String major;//专业
	
	private String degree;//学位
	
	private String majorDesc;//专业
	
	private String studyState;

	public String getPerStudyInfoId() {
		return perStudyInfoId;
	}

	public void setPerStudyInfoId(String perStudyInfoId) {
		this.perStudyInfoId = perStudyInfoId;
	}

	public String getSchoole() {
		return schoole;
	}

	public void setSchoole(String schoole) {
		this.schoole = schoole;
	}

	

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getMajorDesc() {
		return majorDesc;
	}

	public void setMajorDesc(String majorDesc) {
		this.majorDesc = majorDesc;
	}

	public String getStudyState() {
		return studyState;
	}

	public void setStudyState(String studyState) {
		this.studyState = studyState;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}
	
	
	
	
	
}
