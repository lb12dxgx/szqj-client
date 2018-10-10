package com.szqj.before.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.szqj.service.domain.Enterprise;

@Entity
@Table(name = "before_project_result")
public class ProjectResult {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String projectResultId;//反馈结果
	
	@ManyToOne
	@JoinColumn(name = "beforeProject_id")
	private BeforeProject beforeProject;
	
	
	private String projectName;//项目名称
	
	private String enttelphone; //企业电话
   
	
    private String enterpriseId;//所属企业ID
	
	private String enterpriseName; //企业名称
	
	private Date createDate;//创建时间
	
	private String personId;//用户id
	
	private String personName;//姓名
	
	private String telePhone;//电话
	
	private String openid;//微信openId
	
	
	private String result;//管线情况 0：无管线  1：有管线
	
	private String type;//管线类型
	
	private String resultDate;//标注时间
	
	private String resultSumary;//标注说明
	
	

	public String getProjectResultId() {
		return projectResultId;
	}

	public void setProjectResultId(String projectResultId) {
		this.projectResultId = projectResultId;
	}

	

	public BeforeProject getBeforeProject() {
		return beforeProject;
	}

	public void setBeforeProject(BeforeProject beforeProject) {
		this.beforeProject = beforeProject;
	}

	public String getEnttelphone() {
		return enttelphone;
	}

	public void setEnttelphone(String enttelphone) {
		this.enttelphone = enttelphone;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getResultDate() {
		return resultDate;
	}

	public void setResultDate(String resultDate) {
		this.resultDate = resultDate;
	}

	public String getResultSumary() {
		return resultSumary;
	}

	public void setResultSumary(String resultSumary) {
		this.resultSumary = resultSumary;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	
	

}
