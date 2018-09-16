package com.szqj.xcx.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.szqj.redis.RedisService;

public class OpenidFilter implements Filter {

	@Autowired
	private  RedisService redisService;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
	            filterConfig.getServletContext());

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		String openIdMd5 = httpRequest.getHeader("Authorization");
		String openid =redisService.getOpenId(openIdMd5);
		Map<String, String> map=new HashMap<String, String>();
		
	    if(StringUtils.isNotBlank(openid)) {
	   
	    	OpenidRequest openidRequest = new OpenidRequest(httpRequest,openid);
	    	chain.doFilter(openidRequest, response);
	    }else {
	    	HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.setCharacterEncoding("UTF-8");  
			httpResponse.setContentType("application/json; charset=utf-8"); 
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			//httpResponse.getWriter().write("");
	    }

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
