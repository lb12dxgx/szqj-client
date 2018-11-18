package com.szqj.service.domain;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	private String accidentInfoId;
	
	private String accountId;//账号
	
	private String accidentName;//事故名称
	
	private String accidentDesc;//事故描述
	
	private String accidentPlace;//事故地点
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date accidentDate;//事故时间
	
	private String personName;//姓名
	
	private String telePhone;//电话
	
	private String accidentPicId;//事故图片标识
	
	private String accidentVideoId;//事故视频标识
	
	private Date  creatDate;//创建时间
	
	private Integer state=0;//0:待采用  1:已采用  2：不采用 
	
	
	

	public String getAccidentInfoId() {
		return accidentInfoId;
	}

	public void setAccidentInfoId(String accidentInfoId) {
		this.accidentInfoId = accidentInfoId;
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

	public String getAccidentPlace() {
		return accidentPlace;
	}

	public void setAccidentPlace(String accidentPlace) {
		this.accidentPlace = accidentPlace;
	}

	public Date getAccidentDate() {
		return accidentDate;
	}

	public void setAccidentDate(Date accidentDate) {
		this.accidentDate = accidentDate;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	
	
}
