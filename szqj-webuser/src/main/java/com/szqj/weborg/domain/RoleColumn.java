package com.szqj.weborg.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 角色菜单
 * @author lb12
 *
 */
@Entity
@Table(name = "webuser_role_column")
public class RoleColumn {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String roleColumnId;//id
	
	@Column
	private String roleId;//角色id
	
	
	@Column
	private String columnId;//栏目id


	public String getRoleColumnId() {
		return roleColumnId;
	}


	public void setRoleColumnId(String roleColumnId) {
		this.roleColumnId = roleColumnId;
	}


	public String getRoleId() {
		return roleId;
	}


	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


	public String getColumnId() {
		return columnId;
	}


	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}


	

}
