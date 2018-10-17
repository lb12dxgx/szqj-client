package com.szqj.before.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "before_city_area")
public class PersonArea {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String personAreaId;//个人关注区域表
	
	private Integer type;//0:城市  1:区县  2:地域
	
	private String content;//内容
	
	
	private String enterpriseId;//所属企业ID
	
	
	private String personId;//用户id
	
	
	private String openid;//微信openId


	public String getPersonAreaId() {
		return personAreaId;
	}


	public void setPersonAreaId(String personAreaId) {
		this.personAreaId = personAreaId;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getEnterpriseId() {
		return enterpriseId;
	}


	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
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
