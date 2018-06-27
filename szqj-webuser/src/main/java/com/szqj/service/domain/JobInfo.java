package com.szqj.service.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "service_job_info")
public class JobInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String jobInfoId;
	private String enterpriseId;
	private Integer level;//级别  10:普通级别  20:vip  30:超级VIP
	private String jobName;//职位名称
	private String money;//薪资
	private String num;//人数
	private Date startDate;//开始时间
	private Date endDate;//结束时间
	private String jobYear;//工作经验
	private String jobStudyCode;//学历要求
	private String place;//工作地点
	private String jobDesc;//职位信息
	private String person;//联系人
	private String telphone;//联系电话
	private String email;//邮箱
	
	
	public String getJobInfoId() {
		return jobInfoId;
	}
	public void setJobInfoId(String jobInfoId) {
		this.jobInfoId = jobInfoId;
	}
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
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
	public String getJobYear() {
		return jobYear;
	}
	public void setJobYear(String jobYear) {
		this.jobYear = jobYear;
	}
	
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	
	public String getJobStudyCode() {
		return jobStudyCode;
	}
	public void setJobStudyCode(String jobStudyCode) {
		this.jobStudyCode = jobStudyCode;
	}
	public String getJobDesc() {
		return jobDesc;
	}
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	

}
