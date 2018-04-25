package com.szqj.before.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "before_apply")
public class BeforeApply {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String beforeApplyId;//申请ID
	@Column
	private String applyOrgId;//申请所属管理机构id
	@Column
	private Date createDate;//申请开始时间
	@Column
	private String companyId;//申请企业id
	@Column
	private String street;//挖掘街道
	@Column
	private String crossroads;//十字路口
	@Column
	private String identification;//标识
	@Column
	private String tools;//挖掘工具
	@Column
	private String code;//许可证编号
	@Column
	private String party;//工程甲方
	@Column
	private Date startDate;//工程开始时间
	@Column
	private Date applyCode;//申请单号
	@Column
	private Integer state;//状态  0:未受理  1:以受理  2:已回复  3:拒绝
	
	
	public String getBeforeApplyId() {
		return beforeApplyId;
	}
	public void setBeforeApplyId(String beforeApplyId) {
		this.beforeApplyId = beforeApplyId;
	}
	public String getApplyOrgId() {
		return applyOrgId;
	}
	public void setApplyOrgId(String applyOrgId) {
		this.applyOrgId = applyOrgId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCrossroads() {
		return crossroads;
	}
	public void setCrossroads(String crossroads) {
		this.crossroads = crossroads;
	}
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public String getTools() {
		return tools;
	}
	public void setTools(String tools) {
		this.tools = tools;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getApplyCode() {
		return applyCode;
	}
	public void setApplyCode(Date applyCode) {
		this.applyCode = applyCode;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	

	
	
	

}
