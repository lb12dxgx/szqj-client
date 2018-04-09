package com.szqj.weborg.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 *角色用户
 * @author lb12
 *
 */
@Entity
@Table(name = "webuser_role_user")
public class RoleUser {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String roleUserId;
	
	@Column
	private String roleId;
	
	
	@Column
	private String userId;


	public String getRoleUserId() {
		return roleUserId;
	}


	public void setRoleUserId(String roleUserId) {
		this.roleUserId = roleUserId;
	}


	public String getRoleId() {
		return roleId;
	}


	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	
	
	

}
