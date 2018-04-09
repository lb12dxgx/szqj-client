package com.szqj.util;

import java.io.Serializable;

public class SessionMenu implements Serializable{
	
	private String menuId;
	
	private String menuName;//菜单名称
	
	private String menuCode;//菜单编码
	
	private String menuUrl;//菜单url

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	
	

}
