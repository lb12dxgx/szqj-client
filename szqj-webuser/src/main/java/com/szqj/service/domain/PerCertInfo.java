package com.szqj.service.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 个人证书
 * @author lb12
 *
 */

@Entity
@Table(name = "service_per_cert_info")
public class PerCertInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String perCertInfoId;//个人证书
	
	private String personId;//用户id
	
	private String certName;//证书名称
	
	private String startDate;//获得时间
	
	private String certOrgName;//颁发机构
	

	public String getPerCertInfoId() {
		return perCertInfoId;
	}

	public void setPerCertInfoId(String perCertInfoId) {
		this.perCertInfoId = perCertInfoId;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getCertName() {
		return certName;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getCertOrgName() {
		return certOrgName;
	}

	public void setCertOrgName(String certOrgName) {
		this.certOrgName = certOrgName;
	}
	
	
	

}
