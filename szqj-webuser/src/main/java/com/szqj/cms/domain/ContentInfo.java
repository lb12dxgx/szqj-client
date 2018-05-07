package com.szqj.cms.domain;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 内容信息
 * @author lb12
 *
 */
@Entity
@Table(name = "cms_contentinfo")
public class ContentInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String contentId;//内容ID
	
	private String contentTitle;//内容标题
	
	private String contentJson;//内容json
	
	private String content;//内容
	
	
	private String columnId;//栏目ID
	
	private Integer viewNum;//点击
	
	private Integer level;//90:级别1 80:级别2 70:级别3 
	
	private String titleFileId;//标题图
	
	private String contentFileId;//内容图
	
	private String picFileId;//图片集
	
	private String otherFileId;//附件
	
	private Date createDate=new Date();
	
	


	public String getContentId() {
		return contentId;
	}


	public void setContentId(String contentId) {
		this.contentId = contentId;
	}


	public String getContentTitle() {
		return contentTitle;
	}


	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}


	public String getContentJson() {
		return contentJson;
	}


	public void setContentJson(String contentJson) {
		this.contentJson = contentJson;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getColumnId() {
		return columnId;
	}


	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}


	public Integer getViewNum() {
		return viewNum;
	}


	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}


	public String getTitleFileId() {
		return titleFileId;
	}


	public void setTitleFileId(String titleFileId) {
		this.titleFileId = titleFileId;
	}


	public String getContentFileId() {
		return contentFileId;
	}


	public void setContentFileId(String contentFileId) {
		this.contentFileId = contentFileId;
	}


	public String getPicFileId() {
		return picFileId;
	}


	public void setPicFileId(String picFileId) {
		this.picFileId = picFileId;
	}


	public String getOtherFileId() {
		return otherFileId;
	}


	public void setOtherFileId(String otherFileId) {
		this.otherFileId = otherFileId;
	}
	
	public Map getContentJsonMap() {
		ObjectMapper mapper = new ObjectMapper();  
		Map<String, String> m=new HashMap<String, String>();
		try {
			m = mapper.readValue(contentJson, Map.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return m;
		
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public Integer getLevel() {
		return level;
	}


	public void setLevel(Integer level) {
		this.level = level;
	}

	
	
	
}
