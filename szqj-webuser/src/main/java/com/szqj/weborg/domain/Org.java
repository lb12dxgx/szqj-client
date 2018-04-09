package com.szqj.weborg.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 部门信息
 * @author lb12
 *
 */
@Entity
@Table(name = "webuser_org")
public class Org {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String orgId;//部门ID
	
	@Column
	private String orgJsonTree;//部门树

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgJsonTree() {
		return orgJsonTree;
	}

	public void setOrgJsonTree(String orgJsonTree) {
		this.orgJsonTree = orgJsonTree;
	}
	
	
	
	
	
}
