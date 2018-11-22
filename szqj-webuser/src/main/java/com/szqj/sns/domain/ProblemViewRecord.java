package com.szqj.sns.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 问题
 * @author libingbing
 *
 */

@Entity
@Table(name = "sns_problem_view_record")
public class ProblemViewRecord {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String problemViewRecordId;//问题id
	
	private String problemId;//问题id

	private Date createDate;//创建时间
	
	private String personName;//用户名
	
	private String personId;//用户id
	
	private String openid;//微信openId
	
	private String prePersonName;//传播用户名
	
	private String prePersonId;//传播用户id
	
	private String preOpenid;//传播微信openId


	public String getProblemViewRecordId() {
		return problemViewRecordId;
	}

	public void setProblemViewRecordId(String problemViewRecordId) {
		this.problemViewRecordId = problemViewRecordId;
	}

	public String getProblemId() {
		return problemId;
	}

	public void setProblemId(String problemId) {
		this.problemId = problemId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
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

	public String getPrePersonName() {
		return prePersonName;
	}

	public void setPrePersonName(String prePersonName) {
		this.prePersonName = prePersonName;
	}

	public String getPrePersonId() {
		return prePersonId;
	}

	public void setPrePersonId(String prePersonId) {
		this.prePersonId = prePersonId;
	}

	public String getPreOpenid() {
		return preOpenid;
	}

	public void setPreOpenid(String preOpenid) {
		this.preOpenid = preOpenid;
	}
	
	
}
