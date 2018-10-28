package com.szqj.sns.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 名片
 * @author libingbing
 *
 */

@Entity
@Table(name = "sns_card")
public class Card {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String cardId;//名片id
	@Column
	private String personId;//用户id
	@Column
	private String openid;//微信openId
	@Column
	private String enterpriseName;//企业名称
	@Column
	private String personPosition;//个人职位
	
	@Column
	private String personDesc;//一句话描述
	
	@Column
	private String hyType;//行业类型
	@Column
	private String needResource;//我需要资源
	
	@Column
	private String providResource;//我提供资源
	
	
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
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
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getPersonPosition() {
		return personPosition;
	}
	public void setPersonPosition(String personPosition) {
		this.personPosition = personPosition;
	}
	public String getPersonDesc() {
		return personDesc;
	}
	public void setPersonDesc(String personDesc) {
		this.personDesc = personDesc;
	}
	public String getHyType() {
		return hyType;
	}
	public void setHyType(String hyType) {
		this.hyType = hyType;
	}
	public String getNeedResource() {
		return needResource;
	}
	public void setNeedResource(String needResource) {
		this.needResource = needResource;
	}
	public String getProvidResource() {
		return providResource;
	}
	public void setProvidResource(String providResource) {
		this.providResource = providResource;
	}
	
	
	

}
