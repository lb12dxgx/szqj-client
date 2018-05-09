package com.szqj.train.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "train_class")
public class TrainClass {
	 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String trainClassId;
	
	private String trainClassName;//课程名称
	
    private Integer num;//课时
    
    private Date startDate;//开始时间
   
    private String teacherName;//教师名称
    
    private String classSumary;//课程说明
    
    private Date createDate;//创建时间

	public String getTrainClassId() {
		return trainClassId;
	}

	public void setTrainClassId(String trainClassId) {
		this.trainClassId = trainClassId;
	}

	public String getTrainClassName() {
		return trainClassName;
	}

	public void setTrainClassName(String trainClassName) {
		this.trainClassName = trainClassName;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getClassSumary() {
		return classSumary;
	}

	public void setClassSumary(String classSumary) {
		this.classSumary = classSumary;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
	
    

}
