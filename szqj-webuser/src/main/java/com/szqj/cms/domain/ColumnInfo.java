package com.szqj.cms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 栏目信息
 * @author lb12
 *
 */
@Entity
@Table(name = "cms_columninfo")
public class ColumnInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String columnId;//栏目ID
	@Column
	private String columnName;//栏目名称
	
	@Column
	private Integer columnOrder;//栏目顺序
	
	@Column
	private String columnCode;//栏目编码
	
	@Column
	private String columnJson;//栏目json
	
	
	@Column
	private Integer State=0;//0:启用,1:禁用
	
	@Column
	private Integer login=0;//0:不需要登录,1:需要登录
	
	@Column
	private String parentId;//父id

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

	public Integer getColumnOrder() {
		return columnOrder;
	}

	public void setColumnOrder(Integer columnOrder) {
		this.columnOrder = columnOrder;
	}

	
	
	public String getColumnCode() {
		return columnCode;
	}

	public void setColumnCode(String columnCode) {
		this.columnCode = columnCode;
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getLogin() {
		return login;
	}

	public void setLogin(Integer login) {
		this.login = login;
	}
	
	

}
