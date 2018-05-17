package com.szqj.service.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "service_meet")
public class MeetSignUp {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String meetSignUpId;
	
	private String userName;//姓名
	
	private String telphone;//手机
	 
	private String empName;//企业名称
	
	private String post;//职位
	
	private Integer addree;//地址
	
	private Integer email;//邮件
	
	private Integer roomNum;//房间数量
	
	private String  singleRoom;//单间 标间
	
	private Date createDate;//创建时间

	public String getMeetSignUpId() {
		return meetSignUpId;
	}

	public void setMeetSignUpId(String meetSignUpId) {
		this.meetSignUpId = meetSignUpId;
	}

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

	public Integer getAddree() {
		return addree;
	}

	public void setAddree(Integer addree) {
		this.addree = addree;
	}

	public Integer getEmail() {
		return email;
	}

	public void setEmail(Integer email) {
		this.email = email;
	}

	public Integer getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}

	public String getSingleRoom() {
		return singleRoom;
	}

	public void setSingleRoom(String singleRoom) {
		this.singleRoom = singleRoom;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	

	
	
	
}
