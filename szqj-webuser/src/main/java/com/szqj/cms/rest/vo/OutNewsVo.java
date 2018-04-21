package com.szqj.cms.rest.vo;

import java.util.ArrayList;
import java.util.List;

import com.szqj.cms.domain.OutNewsInfo;

public class OutNewsVo {
	
	private String keyword;
	
	private String num;
	
	private List<OutNewsInfo> outNewsInfos=new ArrayList<OutNewsInfo>();

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public List<OutNewsInfo> getOutNewsInfos() {
		return outNewsInfos;
	}

	public void setOutNewsInfos(List<OutNewsInfo> outNewsInfos) {
		this.outNewsInfos = outNewsInfos;
	}

	
	
	
	
	
	

}
