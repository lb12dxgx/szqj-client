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
import com.szqj.service.domain.JobInfo;
import com.szqj.service.domain.JobInfoRepository;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;


@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class EmpJobInfoControle {
	
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	
	
	
	@Autowired
	private JobInfoRepository jobInfoRepository;
	
	/**
	 * 招聘信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/emp/jobinfo.html"  )
	public String jobinfo(@SessionAttribute Account account,Integer pageNum, Integer size, String jobInfoName, ModelMap modelMap){
		PageRequest pageable=Tools.getPage(pageNum, 6);
		Page<JobInfo> page=null;
		Enterprise enterprise = enterpriseRepository.findByAccountId(account.getAccountId()).get(0);
		if(StringUtils.isBlank(jobInfoName)){
			page=jobInfoRepository.findPageByEnterpriseId(enterprise.getEnterpriseId(),pageable);
		}else{
			page=jobInfoRepository.findPageByJobInfoName(jobInfoName,enterprise.getEnterpriseId(),pageable);
		}
		modelMap.put("jobInfoName", jobInfoName);
		modelMap.put("page", page);
		return "emp/jobinfo"; 
	}
	

	
	@RequestMapping(value = "/emp/jobinfo/add.html"  )
	public String jobinfoAdd(@SessionAttribute Account account, ModelMap modelMap){
		Enterprise enterprise = enterpriseRepository.findByAccountId(account.getAccountId()).get(0);
		modelMap.put("enterpriseId", enterprise.getEnterpriseId());
		return "emp/jobinfo_add"; 
	}
	
	
	@RequestMapping(value = "/emp/jobinfo/save.do"  )
	public String jobinfoSave(JobInfo  jobInfo,  ModelMap modelMap){
		String enterpriseName = enterpriseRepository.findById(jobInfo.getEnterpriseId()).get().getEnterpriseName();
		jobInfo.setEnterpriseName(enterpriseName);
		jobInfo.setLevel(10);
		jobInfoRepository.save(jobInfo);
		return "redirect:/emp/jobinfo.html"; 
		
	}
	
	@RequestMapping(value = "/emp/jobinfo/edit.html"  )
	public String jobinfoEdit(String jobInfoId,  ModelMap modelMap){
		JobInfo jobInfo = jobInfoRepository.findById(jobInfoId).get();
		modelMap.put("jobInfo", jobInfo);
		return "emp/jobinfo_edit"; 
	}
	
	
	@RequestMapping(value = "/emp/jobinfo/update.do"  )
	public String jobinfoUpdate(JobInfo  jobInfo,  ModelMap modelMap){
		JobInfo jobInfoRet = jobInfoRepository.findById(jobInfo.getJobInfoId()).get();
		Tools.copyBeanForUpdate(jobInfo, jobInfoRet);
		jobInfoRepository.save(jobInfoRet);
		return "redirect:/emp/jobinfo.html"; 
		
	}
	
	@RequestMapping(value = "/emp/jobinfo/del.do"  )
	public String jobinfoDel(String  jobInfoId,  ModelMap modelMap){
		jobInfoRepository.deleteById(jobInfoId);
		return "redirect:/emp/jobinfo.html"; 
		
	}


}
