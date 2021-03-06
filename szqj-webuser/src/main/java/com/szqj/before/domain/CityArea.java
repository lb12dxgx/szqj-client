package com.szqj.before.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "before_city_area")
public class CityArea {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String cityAreaId;//申请城市地域ID
	
	@Column
	private String areaName;//地域名称
	
	@Column
	private String areaDesc;//网格说明
	
	@Column
	private Integer state;//0:启用   1:禁用
	
	@JsonIgnore 
	@ManyToOne
	@JoinColumn(name = "applyCity_ID")
	private ApplyCity applyCity;
	
	@JsonIgnore 
	@ManyToOne
	@JoinColumn(name = "cityDistrict_ID")
	private CityDistrict cityDistrict;
	
	@Transient
	private boolean checked=false;


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


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}


	public String getAreaDesc() {
		return areaDesc;
	}


	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}


	public boolean isChecked() {
		return checked;
	}


	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	
	

}
