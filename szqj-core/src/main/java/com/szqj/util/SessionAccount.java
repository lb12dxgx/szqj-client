package com.szqj.util;

import java.io.Serializable;

public class SessionAccount implements Serializable{
	
	
	
	private String accountId;//账号1111
	
	private String accountName;//账号
	
	private String userName;//用户名
	
	
	
	private Integer accountType;//类型 (0,云平台管理员；10，总协会管理员;11,总协会业务人员;21,分业务人员；30，企业管理员；31，企业员工)
	
	private Integer state;//状态(0,启用；1，停用；2，删除)
	
	private String orgId;//所属部门ID
	
	private String orgName;//所属部门名称
	
	private String loginStr;//登录返回状态信息
	
	
	private String uPassword;//密码

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getLoginStr() {
		return loginStr;
	}

	public void setLoginStr(String loginStr) {
		this.loginStr = loginStr;
	}

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
