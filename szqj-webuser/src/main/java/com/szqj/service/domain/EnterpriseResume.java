package com.szqj.service.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "service_enterprise_resume")
public class EnterpriseResume {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String enterpriseResumeId;
	
	@ManyToOne
	@JoinColumn(name = "enterprise_id")
	private Enterprise enterprise;
	
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
	

	@ManyToOne
	@JoinColumn(name = "job_info_id")
	private JobInfo jobInfo;
	 
	private Date createDate;
	
	private Integer state=0;//0:待查看,1:已查看,2:通过,3:取消	

	public String getEnterpriseResumeId() {
		return enterpriseResumeId;
	}

	public void setEnterpriseResumeId(String enterpriseResumeId) {
		this.enterpriseResumeId = enterpriseResumeId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public JobInfo getJobInfo() {
		return jobInfo;
	}

	public void setJobInfo(JobInfo jobInfo) {
		this.jobInfo = jobInfo;
	}

	
	
	
}
