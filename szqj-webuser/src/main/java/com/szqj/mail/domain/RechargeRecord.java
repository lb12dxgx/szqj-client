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
	
	private String businessContent;//业务内容
	
	private Double money;//充值金额(元)
	
	private Date createDate;//创建时间
	
	private String personId;//用户id
	
	private String personName;//用户名
	
	private String openid;//微信openId
	
	private Integer state;//0:未到账  1:已到账 2:已退款
	
	private String tradeNo;//订单号32
	
	private String finshDate;//订单号完成时间
	
	private Integer finshMoney;//实际金额
	
	private String transactionId;//微信交易id
	
	

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

	

	public String getBusinessContent() {
		return businessContent;
	}

	public void setBusinessContent(String businessContent) {
		this.businessContent = businessContent;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
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

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getFinshDate() {
		return finshDate;
	}

	public void setFinshDate(String finshDate) {
		this.finshDate = finshDate;
	}

	public Integer getFinshMoney() {
		return finshMoney;
	}

	public void setFinshMoney(Integer finshMoney) {
		this.finshMoney = finshMoney;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	
	
	
	
	
}
