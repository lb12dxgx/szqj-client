package com.szqj.service.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "service_buy_info")
public class BuyInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String buyInfoId;
	
	private String enterpriseId;
	
	private String buyInfoName;//采购名称
	
	private String buyInfoType;//采购分类
	
	private String buyInfoTypeCode;//采购分类编码
	
	private Date createDate;//发布日期
	
	private Date endDate;//截止日期
	
	private Integer num;//采购数量
	
	private String place;//交货地点
	
	private String buyInfoDesc;//采购需求
	
	private String person;//联系人
	
	private String telphone;//联系电话

	

	public String getBuyInfoName() {
		return buyInfoName;
	}

	public void setBuyInfoName(String buyInfoName) {
		this.buyInfoName = buyInfoName;
	}

	public String getBuyInfoType() {
		return buyInfoType;
	}

	public void setBuyInfoType(String buyInfoType) {
		this.buyInfoType = buyInfoType;
	}

	public String getBuyInfoTypeCode() {
		return buyInfoTypeCode;
	}

	public void setBuyInfoTypeCode(String buyInfoTypeCode) {
		this.buyInfoTypeCode = buyInfoTypeCode;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getBuyInfoDesc() {
		return buyInfoDesc;
	}

	public void setBuyInfoDesc(String buyInfoDesc) {
		this.buyInfoDesc = buyInfoDesc;
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

	

	public String getBuyInfoId() {
		return buyInfoId;
	}

	public void setBuyInfoId(String buyInfoId) {
		this.buyInfoId = buyInfoId;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	
	

}
