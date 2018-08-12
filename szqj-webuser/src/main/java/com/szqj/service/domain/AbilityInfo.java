package com.szqj.service.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 企业能力评价
 * @author lb12
 *
 */

@Entity
@Table(name = "service_ability_info")
public class AbilityInfo {
	
	@Id   
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String abilityInfoId;
	
	private String enterpriseId;//企业id
	
	private String enterpriseName; //企业名称
	
	private String abilityInfoType;//能力类型
	
	private String abilityInfoLevel;//能力级别
	
	private String evaluateType;//评价类型
	
	private Date createDate;//提交时间
	
	private Date endDate;//通过时间
	
	private Integer state;//0:待审批  1:审批中  2：审批通过  3：审批失败
	
	private String abilityDocId;//能力附件
	
	private String person;//申请人
	
	private String telphone;//联系电话
	
	@Transient
	private String stateStr;//状态名称
	

	public String getAbilityInfoId() {
		return abilityInfoId;
	}

	public void setAbilityInfoId(String abilityInfoId) {
		this.abilityInfoId = abilityInfoId;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getAbilityInfoType() {
		return abilityInfoType;
	}

	public void setAbilityInfoType(String abilityInfoType) {
		this.abilityInfoType = abilityInfoType;
	}

	public String getAbilityInfoLevel() {
		return abilityInfoLevel;
	}

	public void setAbilityInfoLevel(String abilityInfoLevel) {
		this.abilityInfoLevel = abilityInfoLevel;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getAbilityDocId() {
		return abilityDocId;
	}

	public void setAbilityDocId(String abilityDocId) {
		this.abilityDocId = abilityDocId;
	}

	public String getEvaluateType() {
		return evaluateType;
	}

	public void setEvaluateType(String evaluateType) {
		this.evaluateType = evaluateType;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getStateStr() {
		if(state==1) {
			return "审批中";
		}else if(state==2) {
			return "审批通过";
		}else {
			return "审批失败";
		}
	}
	
	

}
