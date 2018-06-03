package com.szqj.train.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import cn.afterturn.easypoi.excel.annotation.Excel;

@Entity
@Table(name = "train_signup")
public class TrainSignUp {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String trainSignUpId;
	
	@Excel(name = "姓名", height = 20.0, width = 30, isImportField = "true_st", orderNum = "0")
	private String userName;//姓名
	
	@Excel(name = "手机", width = 30, isImportField = "true_st" , orderNum = "1")
	private String telphone;//手机
	@Excel(name = "企业名称",width = 60, isImportField = "true_st" , orderNum = "2")
	private String empName;//企业名称
	
	@Excel(name = "职位",isImportField = "true_st" , orderNum = "3")
	private String post;//职位
	
	@Excel(name = "邮件",isImportField = "true_st" , orderNum = "4")
	private String email;//邮件
	


	@Excel(name = "申请时间", width = 30, isImportField = "true_st",databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd", orderNum = "5")
	private Date createDate;//创建时间
	
	private String trainPlanId;//培训计划ID

	@Excel(name = "签到", width = 30, isImportField = "true_st",replace = { "未签到_0", "已签到_1" }, orderNum = "6")
	private Integer isSign=0;//是否已签到：0:未签到  1：已签到

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTrainSignUpId() {
		return trainSignUpId;
	}

	public void setTrainSignUpId(String trainSignUpId) {
		this.trainSignUpId = trainSignUpId;
	}

	public String getTrainPlanId() {
		return trainPlanId;
	}

	public void setTrainPlanId(String trainPlanId) {
		this.trainPlanId = trainPlanId;
	}

	public Integer getIsSign() {
		return isSign;
	}

	public void setIsSign(Integer isSign) {
		this.isSign = isSign;
	}

	
	
	
	
	
}
