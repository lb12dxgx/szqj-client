package com.szqj.xcx.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class OpenidRequest extends HttpServletRequestWrapper  {
	private Map<String, String[]> parameterMap; // 所有参数的Map集合
	
	private String openid;
	
	public OpenidRequest(HttpServletRequest request,String openid) {
		super(request);
	    parameterMap = request.getParameterMap();
	    this.openid=openid;
	}
	

	public String getParameter(String name) {
		  String str = super.getParameter(name);
	       
	       if("openid".equals(name)) {
	    	   return openid;
	       }
	       return str;
	 }

}
