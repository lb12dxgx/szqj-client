package com.szqj.client.org.bean;

import java.io.Serializable;

/**
 * 客户企业管理
 * @author lb12
 *
 */

public class CustomerCompanyBean implements Serializable {
	
	
	private String customerCompanyId;
	
	private String customerCompanyName; //公司名称
	
	
	private String customerCompanyAddr; //公司地址
	

	private String phone; //公司电话
	
	
	private String summary;


	public String getCustomerCompanyId() {
		return customerCompanyId;
	}


	public void setCustomerCompanyId(String customerCompanyId) {
		this.customerCompanyId = customerCompanyId;
	}


	public String getCustomerCompanyName() {
		return customerCompanyName;
	}


	public void setCustomerCompanyName(String customerCompanyName) {
		this.customerCompanyName = customerCompanyName;
	}


	public String getCustomerCompanyAddr() {
		return customerCompanyAddr;
	}


	public void setCustomerCompanyAddr(String customerCompanyAddr) {
		this.customerCompanyAddr = customerCompanyAddr;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getSummary() {
		return summary;
	}


	public void setSummary(String summary) {
		this.summary = summary;
	} 
	
	
	
}
