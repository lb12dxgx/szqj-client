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
@Table(name = "before_city_district")
public class CityDistrict {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String cityDistrictId;//申请城市区县ID
	
	
	private String districtName;//区县名称
	
	
	@ManyToOne
	@JoinColumn(name = "applyCity_ID")
	private ApplyCity applyCity;

	public String getCityDistrictId() {
		return cityDistrictId;
	}


	public void setCityDistrictId(String cityDistrictId) {
		this.cityDistrictId = cityDistrictId;
	}


	public String getDistrictName() {
		return districtName;
	}


	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}


	public ApplyCity getApplyCity() {
		return applyCity;
	}


	public void setApplyCity(ApplyCity applyCity) {
		this.applyCity = applyCity;
	}


	
	
	

}
