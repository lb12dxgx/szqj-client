package com.szqj.service.domain;

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
	private String personName;//姓名
	
	
	@Column
	private String telePhone;//电话
	
	@Column
	private String userCode;//用户身份证
	
	
	@Column
	private String accountId;//账号
	
	@Column
	private String companyId;//所属企业


	


	

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


	public String getCompanyId() {
		return companyId;
	}


	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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


	public String getAccountId() {
		return accountId;
	}


	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}


	
	

}
