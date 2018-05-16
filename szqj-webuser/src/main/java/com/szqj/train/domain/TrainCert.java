package com.szqj.train.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "train_cert")
public class TrainCert {
	 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String trainCertId;
	
	private String certTypeName;//证书类型
	
    private String  userName;//姓名
    
    private String  userCode;//身份证号
    
    private String certCode;//证书编码
    
    private String certPicPath;//照片
   
    private Date startDate;//开始时间
    
    private Date endDate;//结束时间
    
    private Date createDate;//创建时间
    
    private String trainPlanId;//计划ID
    

	public String getTrainCertId() {
		return trainCertId;
	}

	public void setTrainCertId(String trainCertId) {
		this.trainCertId = trainCertId;
	}

	public String getCertTypeName() {
		return certTypeName;
	}

	public void setCertTypeName(String certTypeName) {
		this.certTypeName = certTypeName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCertCode() {
		return certCode;
	}

	public void setCertCode(String certCode) {
		this.certCode = certCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getTrainPlanId() {
		return trainPlanId;
	}

	public void setTrainPlanId(String trainPlanId) {
		this.trainPlanId = trainPlanId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getCertPicPath() {
		return certPicPath;
	}

	public void setCertPicPath(String certPicPath) {
		this.certPicPath = certPicPath;
	}


	
	
    

}
