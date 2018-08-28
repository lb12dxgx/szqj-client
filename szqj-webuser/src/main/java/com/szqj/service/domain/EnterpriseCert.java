package com.szqj.service.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "service_enterprise_cert")
public class EnterpriseCert {
	 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String enterpriseCertId;
	
	private String certTypeName;//证书类型
	
    private String  enterpriseName;//企业名称
    
    private String certCode;//证书编码
    
    private String level;//证书级别
    
    private String certPicPath;//照片
   
    private Date startDate;//开始时间
    
    private Date endDate;//结束时间
    
    private Date createDate;//创建时间
    
    private String enterpriseId;//企业ID

	public String getEnterpriseCertId() {
		return enterpriseCertId;
	}

	public void setEnterpriseCertId(String enterpriseCertId) {
		this.enterpriseCertId = enterpriseCertId;
	}

	public String getCertTypeName() {
		return certTypeName;
	}

	public void setCertTypeName(String certTypeName) {
		this.certTypeName = certTypeName;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getCertCode() {
		return certCode;
	}

	public void setCertCode(String certCode) {
		this.certCode = certCode;
	}

	public String getCertPicPath() {
		return certPicPath;
	}

	public void setCertPicPath(String certPicPath) {
		this.certPicPath = certPicPath;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
    

	

	
	
    

}
