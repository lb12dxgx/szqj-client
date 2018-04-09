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
@Table(name = "webuser_role_menu")
public class RoleMenu {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String roleMenuId;//id
	
	@Column
	private String roleId;//角色id
	
	
	@Column
	private String menuId;//菜单id


	public String getRoleMenuId() {
		return roleMenuId;
	}


	public void setRoleMenuId(String roleMenuId) {
		this.roleMenuId = roleMenuId;
	}


	public String getRoleId() {
		return roleId;
	}


	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


	public String getMenuId() {
		return menuId;
	}


	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	
	

}
