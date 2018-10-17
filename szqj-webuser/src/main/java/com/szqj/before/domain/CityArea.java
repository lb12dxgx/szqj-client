package com.szqj.before.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "before_city_area")
public class CityArea {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String cityAreaId;//申请城市区县ID
	
	
	private String areaName;//区县名称
	
	
	@ManyToOne
	@JoinColumn(name = "applyCity_ID")
	private ApplyCity applyCity;
	
	
	@ManyToOne
	@JoinColumn(name = "cityDistrict_ID")
	private CityDistrict cityDistrict;


	public String getCityAreaId() {
		return cityAreaId;
	}


	public void setCityAreaId(String cityAreaId) {
		this.cityAreaId = cityAreaId;
	}


	public String getAreaName() {
		return areaName;
	}


	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}


	public ApplyCity getApplyCity() {
		return applyCity;
	}


	public void setApplyCity(ApplyCity applyCity) {
		this.applyCity = applyCity;
	}


	public CityDistrict getCityDistrict() {
		return cityDistrict;
	}


	public void setCityDistrict(CityDistrict cityDistrict) {
		this.cityDistrict = cityDistrict;
	}
	
	
	

}
