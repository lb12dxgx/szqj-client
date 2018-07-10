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
 * 个人用户
 * @author lb12
 *
 */
@Entity
@Table(name = "service_person")
public class Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String personId;//用户id
	@Column
	private String accountId;//账号
	@Column
	private String personName;//姓名
	@Column
	private String personSex;
	@Column
	private String telePhone;//电话
	@Column
	private String userCode;//用户身份证
	@Column
	private String companyId;//所属企业
	@Column
	private String personPicId;//个人照片
	@Column
	private Date birthDate;//生日
	@Column
	private Date jobDate;//工作时间
	@Column
	private String perCity;//居住地
	@Column
	private Float perMoney;//年收入
	@Column
	private String perEmail;//邮箱
	@Column
	private String perSalaryCode;//期望工资编码
	@Column
	private String perSalary;//期望工资
	@Column
	private String workCity;//工作地点
	@Column
	private String jobName;//职位名称
	@Column
	private String hyTypeCode;//行业编码
	
	@Column
	private String hyType;//行业
	
	@Column
	private String jobStudy;//学历
	@Column
	private String jobStudyCode;//学历编码
	@Column
	private String workType;//工作类型
	
	@Column
	private String workStateCode;//工作状态编码
	@Column
	private String workState;//工作状态
	@Column
	private Date createDate=new Date();
	
	
	
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersonSex() {
		return personSex;
	}
	public void setPersonSex(String personSex) {
		this.personSex = personSex;
	}
	public String getTelePhone() {
		return telePhone;
	}
	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getPersonPicId() {
		return personPicId;
	}
	public void setPersonPicId(String personPicId) {
		this.personPicId = personPicId;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Date getJobDate() {
		return jobDate;
	}
	public void setJobDate(Date jobDate) {
		this.jobDate = jobDate;
	}
	public String getPerCity() {
		return perCity;
	}
	public void setPerCity(String perCity) {
		this.perCity = perCity;
	}
	public Float getPerMoney() {
		return perMoney;
	}
	public void setPerMoney(Float perMoney) {
		this.perMoney = perMoney;
	}
	public String getPerEmail() {
		return perEmail;
	}
	public void setPerEmail(String perEmail) {
		this.perEmail = perEmail;
	}
	
	public String getWorkCity() {
		return workCity;
	}
	public void setWorkCity(String workCity) {
		this.workCity = workCity;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getHyType() {
		return hyType;
	}
	public void setHyType(String hyType) {
		this.hyType = hyType;
	}
	public String getJobStudyCode() {
		return jobStudyCode;
	}
	public void setJobStudyCode(String jobStudyCode) {
		this.jobStudyCode = jobStudyCode;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getWorkState() {
		return workState;
	}
	public void setWorkState(String workState) {
		this.workState = workState;
	}
	public String getPerSalary() {
		return perSalary;
	}
	public void setPerSalary(String perSalary) {
		this.perSalary = perSalary;
	}
	public String getPerSalaryCode() {
		return perSalaryCode;
	}
	public void setPerSalaryCode(String perSalaryCode) {
		this.perSalaryCode = perSalaryCode;
	}
	public String getJobStudy() {
		return jobStudy;
	}
	public void setJobStudy(String jobStudy) {
		this.jobStudy = jobStudy;
	}
	public String getWorkStateCode() {
		return workStateCode;
	}
	public void setWorkStateCode(String workStateCode) {
		this.workStateCode = workStateCode;
	}
	public String getHyTypeCode() {
		return hyTypeCode;
	}
	public void setHyTypeCode(String hyTypeCode) {
		this.hyTypeCode = hyTypeCode;
	}
    
   
}
