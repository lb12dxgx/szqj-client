package com.szqj.company.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 企业信息
 * @author lb12
 *
 */

@Entity
@Table(name = "webuser_company")
public class Company {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")  
	private String companyId;//企业id
	
	@Column
	private String name;//企业名称
	
	@Column
	private String shyy;//社会信用
	
	@Column
	private String frdbsfz;//法人代表身份֤
	@Column
	private String frdb;//法人代表
	@Column
	private String lxr;//联系人
	@Column
	private String lxrdh;//联系人电话
	
	@Column
	private String shengcode;//所在省编码
	
	@Column
	private String sheng;//所在省
	
	@Column
	private String shicode;//所在市编码
	
	@Column
	private String shi;//所在市
	
	@Column
	private String zcjb;//注册资本
	
	@Column
	private String zcjbbz;//注册资本币种
	
	@Column
	private String xxdz;//详细地址
	@Column
	private String jyfw;//经营范围
	
	@Column
	private String xydmfjId;//信用代码附件
	
	@Column
	private String accountId;//账号
	
	@Column
	private String invitecode;//邀请码
	
	private Date delDate;
	@Column
	private String delUserId;
	@Column
	private Integer delFlag;

	
	
	

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getFrdbsfz() {
		return frdbsfz;
	}

	public void setFrdbsfz(String frdbsfz) {
		this.frdbsfz = frdbsfz;
	}

	public String getFrdb() {
		return frdb;
	}

	public void setFrdb(String frdb) {
		this.frdb = frdb;
	}

	public String getShyy() {
		return shyy;
	}

	public void setShyy(String shyy) {
		this.shyy = shyy;
	}

	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	public String getLxrdh() {
		return lxrdh;
	}

	public void setLxrdh(String lxrdh) {
		this.lxrdh = lxrdh;
	}

	public String getShengcode() {
		return shengcode;
	}

	public void setShengcode(String shengcode) {
		this.shengcode = shengcode;
	}

	public String getSheng() {
		return sheng;
	}

	public void setSheng(String sheng) {
		this.sheng = sheng;
	}

	public String getShicode() {
		return shicode;
	}

	public void setShicode(String shicode) {
		this.shicode = shicode;
	}

	public String getShi() {
		return shi;
	}

	public void setShi(String shi) {
		this.shi = shi;
	}

	public String getXxdz() {
		return xxdz;
	}

	public void setXxdz(String xxdz) {
		this.xxdz = xxdz;
	}

	public String getJyfw() {
		return jyfw;
	}

	public void setJyfw(String jyfw) {
		this.jyfw = jyfw;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getZcjb() {
		return zcjb;
	}

	public void setZcjb(String zcjb) {
		this.zcjb = zcjb;
	}

	public String getZcjbbz() {
		return zcjbbz;
	}

	public void setZcjbbz(String zcjbbz) {
		this.zcjbbz = zcjbbz;
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

	public String getXydmfjId() {
		return xydmfjId;
	}

	public void setXydmfjId(String xydmfjId) {
		this.xydmfjId = xydmfjId;
	}

	public String getInvitecode() {
		return invitecode;
	}

	public void setInvitecode(String invitecode) {
		this.invitecode = invitecode;
	}
	
	

}
