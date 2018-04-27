package com.szqj.weborg.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 账号信息
 * @author lb12
 *
 */
@Entity
@Table(name = "webuser_account")
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String accountId;//�˺�
	@Column
	private String accountName;//账号
	@Column
	private String accountPassword;//密码
	@Column
	private String userName;//用户名
	@Column
	private Integer accountType;//账号类型(0:管理员,10:业务人员;15:管理部门;20:企业;30:个人�)
	@Column
	private Integer state;//(0:启用,1:禁用,2:删除)
	@Column
	private String orgId;//所属部门Id(业务人员所属部门用户)
	@Column
	private String orgName;//部门名称
	
	@Transient
	private String loginStr;//登录返回状态信息
	
	@Transient
	private String uPassword;//密码
	
	@Transient
	private String token;
	
	
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountPassword() {
		return accountPassword;
	}
	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	

}
