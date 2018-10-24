package com.szqj.before.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "before_project")
public class BeforeProject {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String beforeProjectId;//挖掘项目ID
	
	private String applyCityId;//申请城市ID
	
	private String projectName;//项目名称
	
	private String projectType;//项目类型

	private String projectDistrict;//项目所属区县
	
	private String projectArea;//项目所属地区
	
	private Date projectStartDate;//施工时间
	
	private String projectAddren;//工程地点
	
	private String projectStartEnd;//工程起止点
	
	private String enterpriseId;//所属企业ID
	
	private String enterpriseName; //企业名称
	
	private String enttelphone; //企业电话
	
	private Date createDate;//创建时间
	
	private String personId;//用户id
	
	private String personName;//姓名
	
	private String telePhone;//电话
	
	private String openid;//微信openId
	
	private String mapJson;//地图json
	
	private String picId;//现场照片ID
	
	private String cityDistrictId;//申请城市区县ID
	
	private String cityAreaId;//申请城市地域ID
	
	@Transient
	private String num;//回复数

	public String getBeforeProjectId() {
		return beforeProjectId;
	}

	public void setBeforeProjectId(String beforeProjectId) {
		this.beforeProjectId = beforeProjectId;
	}

	public String getApplyCityId() {
		return applyCityId;
	}

	public void setApplyCityId(String applyCityId) {
		this.applyCityId = applyCityId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public Date getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public String getProjectAddren() {
		return projectAddren;
	}

	public void setProjectAddren(String projectAddren) {
		this.projectAddren = projectAddren;
	}

	public String getProjectStartEnd() {
		return projectStartEnd;
	}

	public void setProjectStartEnd(String projectStartEnd) {
		this.projectStartEnd = projectStartEnd;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getMapJson() {
		return mapJson;
	}

	public void setMapJson(String mapJson) {
		this.mapJson = mapJson;
	}

	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
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

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getEnttelphone() {
		return enttelphone;
	}

	public void setEnttelphone(String enttelphone) {
		this.enttelphone = enttelphone;
	}

	public String getCityDistrictId() {
		return cityDistrictId;
	}

	public void setCityDistrictId(String cityDistrictId) {
		this.cityDistrictId = cityDistrictId;
	}

	public String getCityAreaId() {
		return cityAreaId;
	}

	public void setCityAreaId(String cityAreaId) {
		this.cityAreaId = cityAreaId;
	}

	public String getProjectDistrict() {
		return projectDistrict;
	}

	public void setProjectDistrict(String projectDistrict) {
		this.projectDistrict = projectDistrict;
	}

	public String getProjectArea() {
		return projectArea;
	}

	public void setProjectArea(String projectArea) {
		this.projectArea = projectArea;
	}
	
	
	
	
}
