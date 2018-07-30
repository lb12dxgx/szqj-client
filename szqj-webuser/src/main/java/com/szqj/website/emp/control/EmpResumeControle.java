package com.szqj.website.emp.control;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.EnterpriseRepository;
import com.szqj.service.domain.EnterpriseResumeRepository;
import com.szqj.service.domain.JobInfo;
import com.szqj.service.domain.JobInfoRepository;
import com.szqj.service.domain.Person;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;

/**
 * 企业简历管理
 * @author lb12
 *
 */
@Controller
@RequestMapping("/")
@EnableAutoConfiguration		
public class EmpResumeControle {
	
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	
	
	
	@Autowired
	private JobInfoRepository jobInfoRepository;
	@Autowired
	private EnterpriseResumeRepository enterpriseResumeRepository;
	
	
	
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
	public String myResume(@SessionAttribute Account account,Integer pageNum, Integer size,String jobName,ModelMap modelMap){
		PageRequest pageable=Tools.getPage(pageNum, 6);
		
		Enterprise enterprise = enterpriseRepository.findByAccountId(account.getAccountId()).get(0);
		Page<Person> unPage = enterpriseResumeRepository.findPageaByUn(enterprise.getEnterpriseId(), pageable);
		Page<Person> finshPage = enterpriseResumeRepository.findPageaByFinsh(enterprise.getEnterpriseId(), pageable);
		modelMap.put("unPage", unPage);
		modelMap.put("finshPage", finshPage);
		return "emp/resume_my"; 
	}

}
