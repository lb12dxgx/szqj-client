package com.szqj.train.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "train_teacher")
public class TrainTeacher {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String trainTeacherId;
	
	private String teacherName;//老师名称
	
	private String teacherCode;//证书编码
	
    private String teacherTitle;//老师职称
    
    private Date teacherPosition;//老师职位
   
    private String teacherSumary;//老师说明
     
    private String teacherPicPath;//照片位置
    
    private String startDate;//开始时间
    
    private String endDate;//结束时间
    
    private Date createDate;//创建时间
    
    private Integer indexShow=0;//是否显示首页 1:显示  0：不显示

	public String getTrainTeacherId() {
		return trainTeacherId;
	}

	public void setTrainTeacherId(String trainTeacherId) {
		this.trainTeacherId = trainTeacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherTitle() {
		return teacherTitle;
	}

	public void setTeacherTitle(String teacherTitle) {
		this.teacherTitle = teacherTitle;
	}

	public Date getTeacherPosition() {
		return teacherPosition;
	}

	public void setTeacherPosition(Date teacherPosition) {
		this.teacherPosition = teacherPosition;
	}

	public String getTeacherSumary() {
		return teacherSumary;
	}

	public void setTeacherSumary(String teacherSumary) {
		this.teacherSumary = teacherSumary;
	}

	

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getTeacherCode() {
		return teacherCode;
	}

	public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
	}

	public String getTeacherPicPath() {
		return teacherPicPath;
	}

	public void setTeacherPicPath(String teacherPicPath) {
		this.teacherPicPath = teacherPicPath;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getIndexShow() {
		return indexShow;
	}

	public void setIndexShow(Integer indexShow) {
		this.indexShow = indexShow;
	}

	
    

}
