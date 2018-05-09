package com.szqj.service.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.cms.domain.ColumnInfoRepository;
import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.EnterpriseRepository;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.service.AccountService;



@RestController
@RequestMapping("/system/enterprise/")
@EnableAutoConfiguration
public class EnterpriseRest {
	
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	
	
	//根据账号类型返回账号列表
	@RequestMapping(value = "list.do"  )
	
	public RestJson list( String enterpriseName,   Integer pageNum, Integer size){
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		Page<Enterprise> page = enterpriseRepository.findPageByEnterpriseName(enterpriseName, pageable);
		return RestJson.createSucces(page);
	}
	
	
	//保存账号信息
	@RequestMapping(value = "save.do"  )
	public RestJson save( Enterprise enterprise){
		enterprise.setCreateDate(new Date());
		enterpriseRepository.save(enterprise);
		return RestJson.createSucces(enterprise);
	}
	
	//更新
	@RequestMapping(value = "update.do"  )
	public RestJson update( Enterprise enterprise){
		enterpriseRepository.save(enterprise);
		return RestJson.createSucces(enterprise);
	}
	
	//删除
	@RequestMapping(value = "delete.do"  )
	public RestJson delete( String enterpriseId){
		enterpriseRepository.deleteById(enterpriseId);
		return RestJson.createSucces();
	}
	
	
		
	
	
	

}
