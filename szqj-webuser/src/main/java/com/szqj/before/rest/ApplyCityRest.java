package com.szqj.before.rest;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.before.domain.ApplyCity;
import com.szqj.before.domain.ApplyCityRepository;
import com.szqj.before.domain.ApplyOrg;
import com.szqj.before.domain.ApplyOrgRepository;
import com.szqj.before.service.ApplyOrgService;
import com.szqj.util.ConstantUtils;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;

@RestController
@RequestMapping("/system/applycity")
@EnableAutoConfiguration
public class ApplyCityRest {
	
	@Autowired
	private ApplyCityRepository applyCityRepository;
	
	
	
	
	@RequestMapping(value = "list.do"  )
	public RestJson list(){
		List<ApplyCity> list = applyCityRepository.findAll();
		return RestJson.createSucces(list);
	}
	
	
	
	@RequestMapping(value = "enabled.do"  )
	public RestJson enabled(String applyCityId){
		ApplyCity applyCity = applyCityRepository.findById(applyCityId).get();
		applyCity.setState(1);
		applyCityRepository.save(applyCity);
		return RestJson.createSucces(applyCity);
	}
	
	
	@RequestMapping(value = "disabled.do"  )
	public RestJson disabled(String applyCityId){
		ApplyCity applyCity = applyCityRepository.findById(applyCityId).get();
		applyCity.setState(2);
		applyCityRepository.save(applyCity);
		return RestJson.createSucces();
	}
	
	
	@RequestMapping(value = "save.do")
	public RestJson save(ApplyCity applyCity){
		applyCity.setState(1);
		applyCity.setCreateDate(new Date());
		applyCityRepository.save(applyCity);
		return RestJson.createSucces();
	}
	
	
	
	
	
	

}
