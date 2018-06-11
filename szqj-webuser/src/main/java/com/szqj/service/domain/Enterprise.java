package com.szqj.service.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "service_enterprise")
public class Enterprise {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String enterpriseId;
	
	private String enterpriseName; //企业名称
	
	private String enterpriseCode; //企业信用代码
	
	private String enterpriseSummary; //企业介绍
	
	private String business;//主营业务
	
	private String addree;//地址
	
	private String telphone;//电话
	
	private String quaPicId;//资格图片Id
	
	private String enterprisePicId;//企业介绍图片Id
	
	private String accountId;//账号
	
	private Date createDate;//创建时间
	
	private Integer level;//级别  10:普通级别  20:vip  30:超级VIP
	
	private Integer orderNum;//顺序

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

	public String getEnterpriseSummary() {
		return enterpriseSummary;
	}

	public void setEnterpriseSummary(String enterpriseSummary) {
		this.enterpriseSummary = enterpriseSummary;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getAddree() {
		return addree;
	}

	public void setAddree(String addree) {
		this.addree = addree;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getQuaPicId() {
		return quaPicId;
	}

	public void setQuaPicId(String quaPicId) {
		this.quaPicId = quaPicId;
	}

	public String getEnterprisePicId() {
		return enterprisePicId;
	}

	public void setEnterprisePicId(String enterprisePicId) {
		this.enterprisePicId = enterprisePicId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}
	
	
}
