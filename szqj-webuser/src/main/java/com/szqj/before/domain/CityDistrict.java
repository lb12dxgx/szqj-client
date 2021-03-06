package com.szqj.before.domain;

import java.util.ArrayList;
import java.util.List;

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

@Entity
@Table(name = "before_city_district")
public class CityDistrict {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String cityDistrictId;//申请城市区县ID
	
	private String applyCompany;//申请单位
	@Column
	private String enttelphone; //企业电话
	@Column
	private String person;//联系人
	@Column
	private String telphone;//联系电话 
	
	@Column
	private String districtName;//区县名称
	
	@Column
	private Integer state;//0:启用   1:禁用
	
	
	@ManyToOne
	@JoinColumn(name = "applyCity_ID")
	private ApplyCity applyCity;
	
	@Transient
	private List<CityArea> list=new ArrayList<CityArea>();
	
	@Transient
	private boolean checked=false;

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


	public String getApplyCompany() {
		return applyCompany;
	}


	public void setApplyCompany(String applyCompany) {
		this.applyCompany = applyCompany;
	}


	public String getEnttelphone() {
		return enttelphone;
	}


	public void setEnttelphone(String enttelphone) {
		this.enttelphone = enttelphone;
	}


	public String getPerson() {
		return person;
	}


	public void setPerson(String person) {
		this.person = person;
	}


	public String getTelphone() {
		return telphone;
	}


	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}


	public List<CityArea> getList() {
		return list;
	}


	public void addCityArea(CityArea cityArea) {
		this.list.add(cityArea);
	}


	public void setList(List<CityArea> list) {
		this.list = list;
	}


	public boolean isChecked() {
		return checked;
	}


	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	
	
	
	

}
