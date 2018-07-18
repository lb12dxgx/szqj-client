package com.szqj.service.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 工作经历
 * @author lb12
 * 
 */

@Entity
@Table(name = "service_per_job_info")
public class PerJobInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String perJobInfoId;//工作经历
	
	@Column
	private String personId;//用户id

	private String companyName;//公司名称
	
	private String jobState;//职位状态
	
	private String jobStartDate;//工作开始时间
	
	private String jobEndDate;//工作结束时间

	private String  department;//部门

	private String jobname;//职位
	
	private String workdesc;//workdesc
	

	public String getPerJobInfoId() {
		return perJobInfoId;
	}

	public void setPerJobInfoId(String perJobInfoId) {
		this.perJobInfoId = perJobInfoId;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getJobState() {
		return jobState;
	}

	public void setJobState(String jobState) {
		this.jobState = jobState;
	}

	public String getJobStartDate() {
		return jobStartDate;
	}

	public void setJobStartDate(String jobStartDate) {
		this.jobStartDate = jobStartDate;
	}

	public String getJobEndDate() {
		return jobEndDate;
	}

	public void setJobEndDate(String jobEndDate) {
		this.jobEndDate = jobEndDate;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getJobname() {
		return jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

	public String getWorkdesc() {
		return workdesc;
	}

	public void setWorkdesc(String workdesc) {
		this.workdesc = workdesc;
	}
	
	
	
	
	
}
