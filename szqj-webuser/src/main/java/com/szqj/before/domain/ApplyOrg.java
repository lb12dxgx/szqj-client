package com.szqj.before.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.szqj.util.ConstantUtils;

@Entity
@Table(name = "before_apply_org")
public class ApplyOrg {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String applyOrgId;//申请管理机构ID
	@Column
	private String orgName;//机构名称
	@Column
	private String city;//管理城市 
	@Column
	private String telphone;//机构电话
	@Column
	private String address;//机构地址
	@Column
	private String contacts;//联系人
	@Column
	private String contactsTel;//联系人手机
	@Column
	private String workDate;//工作时间
	@Column
	private String feedback;//反馈时间要求
	@Column
	private Integer state=ConstantUtils.ACCOUNT_STATE_START;//状态
	@Column
	private String accountId;//账号
	
	@Transient
	private String password;//密码
	
	
	public String getApplyOrgId() {
		return applyOrgId;
	}
	public void setApplyOrgId(String applyOrgId) {
		this.applyOrgId = applyOrgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getContactsTel() {
		return contactsTel;
	}
	public void setContactsTel(String contactsTel) {
		this.contactsTel = contactsTel;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

}
