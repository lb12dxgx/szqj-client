package com.szqj.service.domain;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 管线事故
 * @author libingbing
 *
 */
@Entity
@Table(name = "service_accident_info")
public class AccidentInfo {

	@Id   
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String accidentInfod;
	
	private String accountId;//账号
	
	private String accidentName;//事故名称
	
	private String accidentDesc;//事故描述
	
	private String accidentPicId;//事故图片标识
	
	private String accidentVideoId;//事故视频标识
	
	private Date  creatDate;//创建时间
	
	
	public String getAccidentInfod() {
		return accidentInfod;
	}

	public void setAccidentInfod(String accidentInfod) {
		this.accidentInfod = accidentInfod;
	}

	public String getAccidentName() {
		return accidentName;
	}

	public void setAccidentName(String accidentName) {
		this.accidentName = accidentName;
	}

	public String getAccidentDesc() {
		return accidentDesc;
	}

	public void setAccidentDesc(String accidentDesc) {
		this.accidentDesc = accidentDesc;
	}

	public String getAccidentPicId() {
		return accidentPicId;
	}

	public void setAccidentPicId(String accidentPicId) {
		this.accidentPicId = accidentPicId;
	}

	public String getAccidentVideoId() {
		return accidentVideoId;
	}

	public void setAccidentVideoId(String accidentVideoId) {
		this.accidentVideoId = accidentVideoId;
	}

	public Date getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}

	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	
	
}
