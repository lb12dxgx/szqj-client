package com.szqj.sns.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "sns_result")
public class Result {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String resultId;//结果id
	
	@ManyToOne
	@JoinColumn(name = "problem_id")
	private Problem problem;
	
	
	private String answerId;//回答id
	
	private String personId;//用户id
	
	private String personName;//用户名
	
	private String personPosition;//用户名
	
	private String enterpriseName;//企业名
	
	private String openid;//微信openId
	
	private Integer type;//回答级别 1：一级 2: 二级  3:三级
	
	private Integer scoreNum;//获得的积分数量 
	
	
	private Date createDate;//创建时间
	
	@Transient
	private Result twoResult;
	
	@Transient
	private Result threeResult;
	

	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}
	
	
	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Integer getScoreNum() {
		return scoreNum;
	}

	public void setScoreNum(Integer scoreNum) {
		this.scoreNum = scoreNum;
	}

	public Result getTwoResult() {
		return twoResult;
	}

	public void setTwoResult(Result twoResult) {
		this.twoResult = twoResult;
	}

	public Result getThreeResult() {
		return threeResult;
	}

	public void setThreeResult(Result threeResult) {
		this.threeResult = threeResult;
	}
	
	
	

}
