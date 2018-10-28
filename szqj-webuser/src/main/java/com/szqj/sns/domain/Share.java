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

import com.szqj.before.domain.BeforeProject;

/**
 * 分享
 * @author libingbing
 *
 */

@Entity
@Table(name = "sns_share")
public class Share {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String shareId;//分享id
	
	
	@ManyToOne
	@JoinColumn(name = "problem_id")
	private Problem problem;
	
	
	private String personId;//分享用户id
	
	private String openid;//分享微信openId
	
	
	private String prePersonId;//上一分享用户id
	
	private String preOpenid;//上一分享微信openId
	
	private Date createDate;//创建时间

	public String getShareId() {
		return shareId;
	}

	public void setShareId(String shareId) {
		this.shareId = shareId;
	}

	

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
