package com.szqj.util;

import java.util.HashMap;

public enum McJobType {

	MC_CJ("MCCJ", "门窗初级"), MC_ZJ("MCZJ", "门窗中级"), MC_GJ("MCGJ", "门窗高级");

	private String code;
	private String name;
  
	private McJobType(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static HashMap<String, String> getMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		for (McJobType c : McJobType.values()) {
			map.put(c.getCode(), c.getName());
		}

		return map;
	}
	
	public static McJobType getMcJobTypeByCode(String code){
		McJobType m=null;
		for (McJobType c : McJobType.values()) {
			if(c.getCode().equals(code)){
				m=c;
			}
		}
		return m;
	}

}
