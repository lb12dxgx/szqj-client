package com.szqj.service.rest;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.EnterpriseRepository;
import com.szqj.service.domain.Product;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;



@RestController
@RequestMapping("/system/enterprise/")
@EnableAutoConfiguration
public class EnterpriseRest {
	
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	
	

	@RequestMapping(value = "list.do"  )
	public RestJson list( String enterpriseName,   Integer pageNum, Integer size){
		
		Page<Enterprise> page=null;
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		if(StringUtils.isNotBlank(enterpriseName)) {
			page =  enterpriseRepository.findPageByEnterpriseName(enterpriseName, pageable);
		}else {
			page = enterpriseRepository.findPage(pageable);
		}
		
		return RestJson.createSucces(page);
	}
	
	@RequestMapping(value = "get.do"  )
	public RestJson get(String enterpriseId){
		Enterprise enterprise = enterpriseRepository.findById(enterpriseId).get();
		return RestJson.createSucces(enterprise);
	}
	
	
	@RequestMapping(value = "save.do"  )
	public RestJson save( Enterprise enterprise){
		enterprise.setCreateDate(new Date());
		enterpriseRepository.save(enterprise);
		return RestJson.createSucces(enterprise);
	}
	
	
	@RequestMapping(value = "update.do"  )
	public RestJson update( Enterprise enterprise){
		enterpriseRepository.save(enterprise);
		return RestJson.createSucces(enterprise);
	}
	
	
	@RequestMapping(value = "delete.do"  )
	public RestJson delete( String enterpriseId){
		enterpriseRepository.deleteById(enterpriseId);
		return RestJson.createSucces();
	}
	
	
		
	
	
	

}
