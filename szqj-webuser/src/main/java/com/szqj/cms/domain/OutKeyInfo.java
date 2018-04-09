package com.szqj.cms.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 外部抓取信息关键字管理
 * @author lb12
 *
 */
@Entity
@Table(name = "cms_outkey")
public class OutKeyInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String outkeyinfoId;//ID
	
	@Column
	private String keyStr;//百度搜索关键字

	public String getOutkeyinfoId() {
		return outkeyinfoId;
	}

	public void setOutkeyinfoId(String outkeyinfoId) {
		this.outkeyinfoId = outkeyinfoId;
	}

	public String getKeyStr() {
		return keyStr;
	}

	public void setKeyStr(String keyStr) {
		this.keyStr = keyStr;
	}

	
}
