package com.szqj.weborg.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author lb12
 *
 */

@Entity
@Table(name = "webuser_role_usertype")
public class RoleUserType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String roleUserTypeId;
	
	@Column
	private String roleId;
	
	
	@Column
	private Integer userType;


	public String getRoleUserTypeId() {
		return roleUserTypeId;
	}


	public void setRoleUserTypeId(String roleUserTypeId) {
		this.roleUserTypeId = roleUserTypeId;
	}


	public String getRoleId() {
		return roleId;
	}


	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


	public Integer getUserType() {
		return userType;
	}


	public void setUserType(Integer userType) {
		this.userType = userType;
	}

}
