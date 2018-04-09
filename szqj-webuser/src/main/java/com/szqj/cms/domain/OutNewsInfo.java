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
 * 外部抓取信息
 * @author lb12
 *
 */
@Entity
@Table(name = "cms_outnews")
public class OutNewsInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String outnewsinfoId;//内容ID
	
	@Column
	private String title;
	
	@Column
	private String url;
	
	@Column
	private String descr;
	
	@Column
	private Date createDate;
	
	@Column
	private String keyword;
	
	@Column
	private Integer level;//(0：普通,1:重要)
	
	
	@Column
	private Integer State;//(0:未编辑,1:已编辑,3:删除)

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	public String getOutnewsinfoId() {
		return outnewsinfoId;
	}

	public void setOutnewsinfoId(String outnewsinfoId) {
		this.outnewsinfoId = outnewsinfoId;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getState() {
		return State;
	}

	public void setState(Integer state) {
		State = state;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	
	
	

}
