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
	
    private String teacherTitle;//老师职称
    
    private Date teacherPosition;//老师职位
   
    private String teacherSumary;//老师说明
    
    private String teacherPicId;//老师照片

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

	
    

}
