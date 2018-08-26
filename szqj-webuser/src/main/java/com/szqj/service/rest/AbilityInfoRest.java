package com.szqj.service.rest;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.service.domain.AbilityInfo;
import com.szqj.service.domain.AbilityInfoRepository;
import com.szqj.service.domain.JobInfo;
import com.szqj.service.domain.JobInfoRepository;
import com.szqj.service.domain.Meet;
import com.szqj.service.domain.MeetRepository;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;

@RestController
@RequestMapping("/system/abilityinfo/")
@EnableAutoConfiguration
public class AbilityInfoRest {
	
	@Autowired
	private AbilityInfoRepository abilityInfoRepository;
	
	

	@RequestMapping(value = "unlist.do"  )
	public RestJson unlist(String enterpriseName,String abilityInfoType, Integer pageNum, Integer size){
		
		Page<AbilityInfo> page=null;
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		if(StringUtils.isNotBlank(enterpriseName)&&StringUtils.isNotBlank(abilityInfoType)){
			page =  abilityInfoRepository.findAdminPageByeEnterpriseNameAndAbilityInfoType(enterpriseName,abilityInfoType,0, pageable);
		}else if(StringUtils.isNotBlank(enterpriseName)) {
			page =  abilityInfoRepository.findAdminPageByeEnterpriseName(enterpriseName,0, pageable);
		}else if(StringUtils.isNotBlank(abilityInfoType)) {
			page = abilityInfoRepository.findAdminPageByAbilityInfoType(abilityInfoType,0, pageable);
		}else {
			page =abilityInfoRepository.findPage(0,pageable);
		}
		
		return RestJson.createSucces(page);
	}
	
	
	@RequestMapping(value = "passlist.do"  )
	public RestJson passlist(String enterpriseName,String abilityInfoType, Integer pageNum, Integer size){
		
		Page<AbilityInfo> page=null;
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		if(StringUtils.isNotBlank(enterpriseName)&&StringUtils.isNotBlank(abilityInfoType)){
			page =  abilityInfoRepository.findAdminPageByeEnterpriseNameAndAbilityInfoType(enterpriseName,abilityInfoType,1, pageable);
		}else if(StringUtils.isNotBlank(enterpriseName)) {
			page =  abilityInfoRepository.findAdminPageByeEnterpriseName(enterpriseName,1, pageable);
		}else if(StringUtils.isNotBlank(abilityInfoType)) {
			page = abilityInfoRepository.findAdminPageByAbilityInfoType(abilityInfoType,1, pageable);
		}else {
			page =abilityInfoRepository.findPage(1,pageable);
		}
		
		return RestJson.createSucces(page);
	}
	
	
	@RequestMapping(value = "nopasslist.do"  )
	public RestJson nopasslist(String enterpriseName,String abilityInfoType, Integer pageNum, Integer size){
		
		Page<AbilityInfo> page=null;
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		if(StringUtils.isNotBlank(enterpriseName)&&StringUtils.isNotBlank(abilityInfoType)){
			page =  abilityInfoRepository.findAdminPageByeEnterpriseNameAndAbilityInfoType(enterpriseName,abilityInfoType,2, pageable);
		}else if(StringUtils.isNotBlank(enterpriseName)) {
			page =  abilityInfoRepository.findAdminPageByeEnterpriseName(enterpriseName,2, pageable);
		}else if(StringUtils.isNotBlank(abilityInfoType)) {
			page = abilityInfoRepository.findAdminPageByAbilityInfoType(abilityInfoType,2, pageable);
		}else {
			page =abilityInfoRepository.findPage(2,pageable);
		}
		
		return RestJson.createSucces(page);
	}
	
	
	
	@RequestMapping(value = "get.do"  )
	public RestJson get(String abilityInfoId){
		AbilityInfo abilityInfo = abilityInfoRepository.findById(abilityInfoId).get();
		return RestJson.createSucces(abilityInfo);
	}
	
	
	@RequestMapping(value = "changePass.do"  )
	public RestJson changePass(String abilityInfoId){
		AbilityInfo abilityInfo = abilityInfoRepository.findById(abilityInfoId).get();
		abilityInfo.setState(1);
		abilityInfo.setEndDate(new Date());
		abilityInfoRepository.save(abilityInfo);
		return RestJson.createSucces();
	}
	
	@RequestMapping(value = "changeNoPass.do"  )
	public RestJson changeNoPass(String abilityInfoId,String suggestions){
		AbilityInfo abilityInfo = abilityInfoRepository.findById(abilityInfoId).get();
		abilityInfo.setState(2);
		abilityInfo.setSuggestions(suggestions);
		abilityInfoRepository.save(abilityInfo);
		return RestJson.createSucces();
	}
	

}
