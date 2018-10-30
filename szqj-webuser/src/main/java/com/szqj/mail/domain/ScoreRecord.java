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
@Table(name = "mail_score_record")
public class ScoreRecord {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String scoreRecordId;//礼物id
	
	private String businessName;//业务名称
	
	private String businessId;//业务名称
	
	private Integer businessType;//记录类型 0:添加积分  1：扣除积分
	
	private Integer num;//积分数量 扣除为负 添加为正
	
	private Date createDate;//创建时间
	
	private String personId;//用户id
	
	private String personName;//用户名
	
	private String openid;//微信openId
	
	

	public String getScoreRecordId() {
		return scoreRecordId;
	}

	public void setScoreRecordId(String scoreRecordId) {
		this.scoreRecordId = scoreRecordId;
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

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	
	

}
