package com.szqj.service.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "service_enterprise")
public class Enterprise {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String enterpriseId;
	
	private String enterpriseName; //企业名称
	
	private String enterpriseCode; //企业信用代码
	
	private String enterpriseSummary; //企业介绍
	
	private String business;//主营业务
	
	private String addree;//地址
	
	private String enttelphone;//公司电话
	
	private String quaPicId;//资格图片Id
	
	private String enterprisePicId;//企业介绍图片Id
	
	private String accountId;//账号
	
	private Date createDate=new Date();//创建时间
	
	private String hyTypeCode;//行业类型编码
	
	private String hyType;//行业类型
	
	private String frName;//法人名称
	
	private String frCode;//法人身份证号
	
	private String lxName;//联系人姓名
	
	private String telphone;//联系人电话
	
	private Integer level;//级别  10:普通级别  20:vip  30:超级VIP
	
	private Integer jobLevel;//级别  10:普通招聘  20:名企招聘
	
	private String qyXjCode;//企业性质编码
	
	private String qyXj;//企业性质
	
	private String qyGmCode;//企业规模
	
	private String qyGm;//企业规模
	
	private Integer orderNum;//顺序
	
	@Column
	private String openid;
	
	@Transient
	private String enterprisePicPath;//企业图片
	

	

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

	public String getEnterpriseSummary() {
		return enterpriseSummary;
	}

	public void setEnterpriseSummary(String enterpriseSummary) {
		this.enterpriseSummary = enterpriseSummary;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getAddree() {
		return addree;
	}

	public void setAddree(String addree) {
		this.addree = addree;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getQuaPicId() {
		return quaPicId;
	}

	public void setQuaPicId(String quaPicId) {
		this.quaPicId = quaPicId;
	}

	public String getEnterprisePicId() {
		return enterprisePicId;
	}

	public void setEnterprisePicId(String enterprisePicId) {
		this.enterprisePicId = enterprisePicId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public String getHyType() {
		return hyType;
	}

	public void setHyType(String hyType) {
		this.hyType = hyType;
	}

	public String getFrName() {
		return frName;
	}

	public void setFrName(String frName) {
		this.frName = frName;
	}

	public String getFrCode() {
		return frCode;
	}

	public void setFrCode(String frCode) {
		this.frCode = frCode;
	}

	public String getLxName() {
		return lxName;
	}

	public void setLxName(String lxName) {
		this.lxName = lxName;
	}

	public String getEnttelphone() {
		return enttelphone;
	}

	public void setEnttelphone(String enttelphone) {
		this.enttelphone = enttelphone;
	}

	public Integer getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(Integer jobLevel) {
		this.jobLevel = jobLevel;
	}

	public String getQyXjCode() {
		return qyXjCode;
	}

	public void setQyXjCode(String qyXjCode) {
		this.qyXjCode = qyXjCode;
	}

	public String getQyXj() {
		return qyXj;
	}

	public void setQyXj(String qyXj) {
		this.qyXj = qyXj;
	}

	public String getQyGmCode() {
		return qyGmCode;
	}

	public void setQyGmCode(String qyGmCode) {
		this.qyGmCode = qyGmCode;
	}

	public String getQyGm() {
		return qyGm;
	}

	public void setQyGm(String qyGm) {
		this.qyGm = qyGm;
	}

	public String getHyTypeCode() {
		return hyTypeCode;
	}

	public void setHyTypeCode(String hyTypeCode) {
		this.hyTypeCode = hyTypeCode;
	}

	public String getEnterprisePicPath() {
		return enterprisePicPath;
	}

	public void setEnterprisePicPath(String enterprisePicPath) {
		this.enterprisePicPath = enterprisePicPath;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	
	
	
}
