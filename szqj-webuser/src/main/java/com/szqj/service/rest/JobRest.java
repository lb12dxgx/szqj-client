package com.szqj.service.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.service.domain.JobInfo;
import com.szqj.service.domain.JobInfoRepository;
import com.szqj.service.domain.Meet;
import com.szqj.service.domain.MeetRepository;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;

@RestController
@RequestMapping("/system/job/")
@EnableAutoConfiguration
public class JobRest {
	
	@Autowired
	private JobInfoRepository jobInfoRepository;
	
	

	@RequestMapping(value = "list.do"  )
	public RestJson list( String jobName,String enterpriseName,Integer pageNum, Integer size){
		
		Page<JobInfo> page=null;
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		if(StringUtils.isNotBlank(jobName)) {
			page =  jobInfoRepository.findAdminPageByJobInfoName(jobName, pageable);
		}else if(StringUtils.isNotBlank(enterpriseName)) {
			page = jobInfoRepository.findAdminPageByEnterpriseName(enterpriseName, pageable);
		}else {
			page =jobInfoRepository.findPage(pageable);
		}
		
		return RestJson.createSucces(page);
	}
	
	
	@RequestMapping(value = "get.do"  )
	public RestJson get(String jobInfoId){
		JobInfo jobInfo = jobInfoRepository.findById(jobInfoId).get();
		return RestJson.createSucces(jobInfo);
	}
	
	
	@RequestMapping(value = "changeLevel.do"  )
	public RestJson changeLevel(String jobInfoId){
		JobInfo jobInfo = jobInfoRepository.findById(jobInfoId).get();
		if(jobInfo.getLevel()==10) {
			jobInfo.setLevel(20);
		}else {
			jobInfo.setLevel(10);
		}
	
		jobInfoRepository.save(jobInfo);
		return RestJson.createSucces();
	}
	

}
