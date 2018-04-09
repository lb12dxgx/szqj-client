package com.szqj.cms.rest.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

public class ColumnInfoNode {
	
	private String columnId;
	
	private String columnName;
	
	private String columnCode;
	
	private String columnJson;//栏目json
	
	private Integer State=0;//0:启用,1:禁用
	
	private Integer login=0;//0:不需要登录,1:需要登录
	
	private String parentId;
	
	private List<ColumnInfoNode> children=new ArrayList<ColumnInfoNode>(); 

	

	public String getColumnId() {
		return columnId;
	}



	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}



	public String getColumnName() {
		return columnName;
	}



	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}



	public String getParentId() {
		return parentId;
	}



	public void setParentId(String parentId) {
		this.parentId = parentId;
	}



	public List<ColumnInfoNode> getChildren() {
		return children;
	}



	public void setChildren(List<ColumnInfoNode> children) {
		this.children = children;
	}



	public String getColumnCode() {
		return columnCode;
	}



	public void setColumnCode(String columnCode) {
		this.columnCode = columnCode;
	}



	public void addNode(ColumnInfoNode node){
		if(children==null){
			children=new ArrayList<ColumnInfoNode>();
		}
		children.add(node);
	}



	public String getColumnJson() {
		return columnJson;
	}



	public void setColumnJson(String columnJson) {
		this.columnJson = columnJson;
	}



	public Integer getState() {
		return State;
	}



	public void setState(Integer state) {
		State = state;
	}



	public Integer getLogin() {
		return login;
	}



	public void setLogin(Integer login) {
		this.login = login;
	}


	
	

}
