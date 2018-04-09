package com.szqj.util;

public class RestJson {
	
	private Integer state;
	
	private Object retData;
	
	
	public static Integer SUCCESS=1;
	public static Integer ERROR=0;

	static public RestJson createSucces(Object ob){
		RestJson restJson=new RestJson();
		restJson.state=SUCCESS;
		restJson.retData=ob;
		return restJson;
	}
	
	
	static public RestJson createError(Object ob){
		RestJson restJson=new RestJson();
		restJson.state=ERROR;
		restJson.retData=ob;
		return restJson;
	}
	
	static public RestJson createSucces(){
		RestJson restJson=new RestJson();
		restJson.state=SUCCESS;
		return restJson;
	}
	
	static public RestJson createError(){
		RestJson restJson=new RestJson();
		restJson.state=ERROR;
		return restJson;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Object getRetData() {
		return retData;
	}

	public void setRetData(Object retData) {
		this.retData = retData;
	}

	
	
	

}
