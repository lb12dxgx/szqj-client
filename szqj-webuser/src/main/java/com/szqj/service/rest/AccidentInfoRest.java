package com.szqj.service.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.service.domain.AbilityInfo;
import com.szqj.service.domain.AccidentInfo;
import com.szqj.service.domain.AccidentInfoRepository;
import com.szqj.service.domain.JobInfo;
import com.szqj.service.domain.JobInfoRepository;
import com.szqj.service.domain.Meet;
import com.szqj.service.domain.MeetRepository;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;

@RestController
@RequestMapping("/system/accidentinfo/")
@EnableAutoConfiguration
public class AccidentInfoRest {
	
	@Autowired
	private AccidentInfoRepository accidentInfoRepository;
	
	

	@RequestMapping(value = "unlist.do"  )
	public RestJson unlist( String accidentName,Integer pageNum, Integer size){
		
		Page<AccidentInfo> page=null;
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		if(StringUtils.isNotBlank(accidentName)) {
			page =  accidentInfoRepository.findUnPageByAccidentName(accidentName, pageable);
		}else {
			page =accidentInfoRepository.findUnPage(pageable);
		}
		
		return RestJson.createSucces(page);
	}
	
	

	@RequestMapping(value = "passlist.do"  )
	public RestJson passlist( String accidentName,Integer pageNum, Integer size){
		
		Page<AccidentInfo> page=null;
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		if(StringUtils.isNotBlank(accidentName)) {
			page =  accidentInfoRepository.findPassPageByAccidentName(accidentName, pageable);
		}else {
			page =accidentInfoRepository.findPassPage(pageable);
		}
		
		return RestJson.createSucces(page);
	}
	
	
	@RequestMapping(value = "nopasslist.do"  )
	public RestJson nopasslist( String accidentName,Integer pageNum, Integer size){
		
		Page<AccidentInfo> page=null;
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		if(StringUtils.isNotBlank(accidentName)) {
			page =  accidentInfoRepository.findNoPassPageByAccidentName(accidentName, pageable);
		}else {
			page =accidentInfoRepository.findNoPassPage(pageable);
		}
		
		return RestJson.createSucces(page);
	}
	
	
	
	@RequestMapping(value = "get.do"  )
	public RestJson get(String accidentInfoId){
		AccidentInfo accidentInfo = accidentInfoRepository.findById(accidentInfoId).get();
		return RestJson.createSucces(accidentInfo);
	}
	
	
	@RequestMapping(value = "changePass.do"  )
	public RestJson changePass(String accidentInfoId){
		AccidentInfo accidentInfo = accidentInfoRepository.findById(accidentInfoId).get();
		accidentInfo.setState(1);
		accidentInfoRepository.save(accidentInfo);
		return RestJson.createSucces();
	}
	
	@RequestMapping(value = "changeNoPass.do"  )
	public RestJson changeNoPass(String accidentInfoId){
		AccidentInfo accidentInfo = accidentInfoRepository.findById(accidentInfoId).get();
		accidentInfo.setState(2);
		accidentInfoRepository.save(accidentInfo);
		return RestJson.createSucces();
	}

}
