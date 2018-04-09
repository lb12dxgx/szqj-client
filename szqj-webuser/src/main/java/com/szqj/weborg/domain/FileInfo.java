package com.szqj.weborg.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.szqj.util.Tools;


/**
 * 企业基本信息
 * @author lb12
 *
 */
@Entity
@Table(name = "webuser_file")
public class FileInfo  implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String fileInfoId;//企业基本信息id
	
	@Column
	private String fileName;//附件名称
	
	@Column
	private String fileType;//附件类型
	@Column
	private String filePath;//附件地址
	@Column
	private String fileWebPath;//附件地址
	@Column
	private String bussinessId;//业务ID
	@Column
	private Date createDate;
	@Column
	private String createUserId;
	@Column
	private Date updateDate;
	@Column
	private String updateUserId;
	@Column
	private Date delDate;
	@Column
	private String delUserId;
	@Column
	private Integer delFlag;
	
	public String getFileInfoId() {
		return fileInfoId;
	}
	public void setFileInfoId(String fileInfoId) {
		this.fileInfoId = fileInfoId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	public Date getDelDate() {
		return delDate;
	}
	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}
	public String getDelUserId() {
		return delUserId;
	}
	public void setDelUserId(String delUserId) {
		this.delUserId = delUserId;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public String getFileWebPath() {
		return fileWebPath;
	}
	public void setFileWebPath(String fileWebPath) {
		this.fileWebPath = fileWebPath;
	}
	public String getBussinessId() {
		return bussinessId;
	}
	public void setBussinessId(String bussinessId) {
		this.bussinessId = bussinessId;
	}

	
	

}
