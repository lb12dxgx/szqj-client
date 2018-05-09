package com.szqj.service.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "service_zbinfo")
public class ZbInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String zbInfoId;//招标项目ID
	
	private String zbXmName;//招标项目名称
	
	private Date publishDate;//发布日期
	
	private Date endDate;//截止日期
	
	private String area;//地址
	
	private String zbContent;//招标文件内容
	
	

	public String getZbXmName() {
		return zbXmName;
	}

	public void setZbXmName(String zbXmName) {
		this.zbXmName = zbXmName;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getZbContent() {
		return zbContent;
	}

	public void setZbContent(String zbContent) {
		this.zbContent = zbContent;
	}

	public String getZbInfoId() {
		return zbInfoId;
	}

	public void setZbInfoId(String zbInfoId) {
		this.zbInfoId = zbInfoId;
	}
	
	

}
