package com.szqj.website.emp.control;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.szqj.weborg.domain.Account;

/**
 * 企业简历管理
 * @author lb12
 *
 */
		
public class EmpResumeControle {
	
	
	/**
	 * 
	 * 
	 * @param pageNum
	 * @param size
	 * @param workCity 地区
	 * @param hyType 行业
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/emp/resume/search.html")
	public String searchResume(Integer pageNum, Integer size,String workCity,String hyType,ModelMap modelMap){
		
		return "emp/resume_search"; 
	}
	
	
	/**
	 * 收到简历信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/emp/resume/admin.html")
	public String myResume(@SessionAttribute Account account,Integer pageNum, Integer size,String jobName,String hyType,ModelMap modelMap){
		
		return "emp/resume_my"; 
	}

}
