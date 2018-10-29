package com.szqj.mail.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 充值金额
 * @author lb12
 *
 */
@Entity
@Table(name = "mail_recharge_record")
public class RechargeRecord {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String rechargeRecordId;//充值记录ID
	
	private String businessType;//业务类型
	
	private String businessConteng;//业务内容
	
	private Integer money;//充值金额
	
	private Date createDate;//创建时间
	
	private String personId;//用户id
	
	private String personName;//用户名
	
	private String openid;//微信openId
	
	private Integer state;//0:未到账  1:已到账
	
	

	public String getRechargeRecordId() {
		return rechargeRecordId;
	}

	public void setRechargeRecordId(String rechargeRecordId) {
		this.rechargeRecordId = rechargeRecordId;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getBusinessConteng() {
		return businessConteng;
	}

	public void setBusinessConteng(String businessConteng) {
		this.businessConteng = businessConteng;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
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

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	
	
	
}
