package com.szqj.train.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "train_plan")
public class TrainPlan {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String trainPlanId;
	
	private String trainName;//培训名称
	
    private String trainAddr;//培训地点
    
    private Date trainStartDate;//培训开始时间
    
    private Date trainEndDate;//培训结束时间
    
    private Integer personNum;//培训人数
    
    private Integer showMain;//1:单独显示,0:列表显示
    
    private Integer trainMoney;//培训费
    
    private String trainSumary;//培训说明
    
    private Date createDate;//创建时间

	public String getTrainPlanId() {
		return trainPlanId;
	}

	public void setTrainPlanId(String trainPlanId) {
		this.trainPlanId = trainPlanId;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getTrainAddr() {
		return trainAddr;
	}

	public void setTrainAddr(String trainAddr) {
		this.trainAddr = trainAddr;
	}

	public Date getTrainStartDate() {
		return trainStartDate;
	}

	public void setTrainStartDate(Date trainStartDate) {
		this.trainStartDate = trainStartDate;
	}

	public Date getTrainEndDate() {
		return trainEndDate;
	}

	public void setTrainEndDate(Date trainEndDate) {
		this.trainEndDate = trainEndDate;
	}

	public Integer getPersonNum() {
		return personNum;
	}

	public void setPersonNum(Integer personNum) {
		this.personNum = personNum;
	}

	public Integer getTrainMoney() {
		return trainMoney;
	}

	public void setTrainMoney(Integer trainMoney) {
		this.trainMoney = trainMoney;
	}

	public String getTrainSumary() {
		return trainSumary;
	}

	public void setTrainSumary(String trainSumary) {
		this.trainSumary = trainSumary;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getShowMain() {
		return showMain;
	}

	public void setShowMain(Integer showMain) {
		this.showMain = showMain;
	}
    
    

}
