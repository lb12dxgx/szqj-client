package com.szqj.mail.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 积分记录
 * @author libingbing
 *
 */

@Entity
@Table(name = "mail_money_record")
public class MoneyRecord {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String moneyRecordId;//礼物id
	
	private String businessName;//业务名称
	
	private Integer businessType;//记录类型 0:添加积分  1：扣除积分
	
	private Integer num;//积分数量 扣除为负 添加为正
	
	private Date createDate;//创建时间
	
	private String personId;//用户id
	
	private String openid;//微信openId
	
	

	public String getMoneyRecordId() {
		return moneyRecordId;
	}

	public void setMoneyRecordId(String moneyRecordId) {
		this.moneyRecordId = moneyRecordId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
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

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	

}
