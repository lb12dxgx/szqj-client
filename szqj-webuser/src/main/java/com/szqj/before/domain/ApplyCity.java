package com.szqj.before.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "before_apply_city")
public class ApplyCity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String applyCityId;//申请城市ID
	
	@Column
	private String cityName;//城市名称
	@Column
	private String applyCompany;//申请单位
	@Column
	private String person;//联系人
	@Column
	private String telphone;//联系电话 
	@Column
	private Date createDate;//创建时间
	
	
	public String getApplyCityId() {
		return applyCityId;
	}
	public void setApplyCityId(String applyCityId) {
		this.applyCityId = applyCityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getApplyCompany() {
		return applyCompany;
	}
	public void setApplyCompany(String applyCompany) {
		this.applyCompany = applyCompany;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	

}
