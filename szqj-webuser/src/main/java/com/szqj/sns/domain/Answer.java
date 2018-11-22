package com.szqj.sns.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 回答
 * @author libingbing
 *
 */


@Entity
@Table(name = "sns_answer")
public class Answer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String answerId;//回答id
	
	@ManyToOne
	@JoinColumn(name = "problem_id")
	private Problem problem;
	
	
	private String content;//内容
	
	private String personId;//用户id
	
	private String personName;//用户名
	
	private String personPosition;//用户名
	
	private String enterpriseName;//企业名
	
	private String openid;//微信openId
	
	private String shareCode;//微信openId
	
	private String prePersonId;//用户id
	
	private String preOpenid;//微信openId
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date createDate;//创建时间

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public String getShareCode() {
		return shareCode;
	}

	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
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

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonPosition() {
		return personPosition;
	}

	public void setPersonPosition(String personPosition) {
		this.personPosition = personPosition;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	
	

}
