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
@Table(name = "service_per_train_info")
public class PerTrainInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String perTrainInfoId;//个人培训id
	
	private String personId;//用户id
	
	private String trainClassName;//培训课程名称
	
	private String startDate;//开始时间
	
	private String endDate;//结束时间
	
	private String trainOrgName;//培训机构名称
	
	private String trainPlace;//培训地点
	
	private String trainDesc;//培训说明
	
	

	public String getPerTrainInfoId() {
		return perTrainInfoId;
	}

	public void setPerTrainInfoId(String perTrainInfoId) {
		this.perTrainInfoId = perTrainInfoId;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getTrainClassName() {
		return trainClassName;
	}

	public void setTrainClassName(String trainClassName) {
		this.trainClassName = trainClassName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTrainOrgName() {
		return trainOrgName;
	}

	public void setTrainOrgName(String trainOrgName) {
		this.trainOrgName = trainOrgName;
	}

	public String getTrainPlace() {
		return trainPlace;
	}

	public void setTrainPlace(String trainPlace) {
		this.trainPlace = trainPlace;
	}

	public String getTrainDesc() {
		return trainDesc;
	}

	public void setTrainDesc(String trainDesc) {
		this.trainDesc = trainDesc;
	}
	

	
	
	
	

}
