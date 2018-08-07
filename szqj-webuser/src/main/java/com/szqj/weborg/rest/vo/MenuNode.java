package com.szqj.weborg.rest.vo;

import java.util.ArrayList;
import java.util.List;

public class MenuNode {
	
	private String menuId;
	
	private String menuName;
	
	private String menuUrl;
	
	private String parentMenuId;
	
	private String code;
	
	private List<MenuNode> children=new ArrayList<MenuNode>(); 

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

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public List<MenuNode> getChildren() {
		return children;
	}
	
	

	public void setChildren(List<MenuNode> children) {
		this.children = children;
	}

	public void addNode(MenuNode node){
		if(children==null){
			children=new ArrayList<MenuNode>();
		}
		children.add(node);
	}

	public String getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
	

}
