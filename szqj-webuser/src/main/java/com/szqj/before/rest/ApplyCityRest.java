package com.szqj.before.rest;

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
	public RestJson list(String cityName, Integer pageNum, Integer size){
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		Page<ApplyCity> page =null;
		if(StringUtils.isBlank(cityName)){
			page = applyCityRepository.findPage(pageable);
		}else {
			page = applyCityRepository.findPageByCityName(cityName,pageable);
		}
		return RestJson.createSucces(page);
	}
	
	
	
	
	
	

}
