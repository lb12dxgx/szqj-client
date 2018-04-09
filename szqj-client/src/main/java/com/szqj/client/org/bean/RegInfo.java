package com.szqj.client.org.bean;

import java.io.Serializable;

public class RegInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String loginName;//登录名
	
	private String passWord; //密码
	
	private String telPhone;//电话
	
	private String smsCode;//短信验证码
	
	private String companyName;//企业名称
	
	private String perName;//姓名
	
	
	private String shengcode;//所在省编码
	
	
	private String sheng;//所在省
	
	
	private String shicode;//所在市编码
	
	
	private String shi;//所在市
	
	private String qucode;//所在区编码
	
	
	private String qu;//所在区
	

	
	
	private String companyId;//所属总协会
	

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPerName() {
		return perName;
	}

	public void setPerName(String perName) {
		this.perName = perName;
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

	public String getQucode() {
		return qucode;
	}

	public void setQucode(String qucode) {
		this.qucode = qucode;
	}

	public String getQu() {
		return qu;
	}

	public void setQu(String qu) {
		this.qu = qu;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((loginName == null) ? 0 : loginName.hashCode());
		result = prime * result + ((passWord == null) ? 0 : passWord.hashCode());
		result = prime * result + ((perName == null) ? 0 : perName.hashCode());
		result = prime * result + ((qu == null) ? 0 : qu.hashCode());
		result = prime * result + ((qucode == null) ? 0 : qucode.hashCode());
		result = prime * result + ((sheng == null) ? 0 : sheng.hashCode());
		result = prime * result + ((shengcode == null) ? 0 : shengcode.hashCode());
		result = prime * result + ((shi == null) ? 0 : shi.hashCode());
		result = prime * result + ((shicode == null) ? 0 : shicode.hashCode());
		result = prime * result + ((smsCode == null) ? 0 : smsCode.hashCode());
		result = prime * result + ((telPhone == null) ? 0 : telPhone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegInfo other = (RegInfo) obj;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (loginName == null) {
			if (other.loginName != null)
				return false;
		} else if (!loginName.equals(other.loginName))
			return false;
		if (passWord == null) {
			if (other.passWord != null)
				return false;
		} else if (!passWord.equals(other.passWord))
			return false;
		if (perName == null) {
			if (other.perName != null)
				return false;
		} else if (!perName.equals(other.perName))
			return false;
		if (qu == null) {
			if (other.qu != null)
				return false;
		} else if (!qu.equals(other.qu))
			return false;
		if (qucode == null) {
			if (other.qucode != null)
				return false;
		} else if (!qucode.equals(other.qucode))
			return false;
		if (sheng == null) {
			if (other.sheng != null)
				return false;
		} else if (!sheng.equals(other.sheng))
			return false;
		if (shengcode == null) {
			if (other.shengcode != null)
				return false;
		} else if (!shengcode.equals(other.shengcode))
			return false;
		if (shi == null) {
			if (other.shi != null)
				return false;
		} else if (!shi.equals(other.shi))
			return false;
		if (shicode == null) {
			if (other.shicode != null)
				return false;
		} else if (!shicode.equals(other.shicode))
			return false;
		if (smsCode == null) {
			if (other.smsCode != null)
				return false;
		} else if (!smsCode.equals(other.smsCode))
			return false;
		if (telPhone == null) {
			if (other.telPhone != null)
				return false;
		} else if (!telPhone.equals(other.telPhone))
			return false;
		return true;
	}
	
	

}
