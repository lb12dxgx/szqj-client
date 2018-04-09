package com.szqj.reg.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.company.domain.Company;
import com.szqj.company.service.CompanyService;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.service.AccountService;

@RestController
@RequestMapping("/reg/company/")
@EnableAutoConfiguration
public class CompanyRegRest {
	
	
	@Autowired
	private CompanyService companyService;
	
	
	//根据账号类型返回账号列表
	@RequestMapping(value = "reg.do"  )
	public RestJson reg(Company company){
		companyService.reg(company);
		return RestJson.createSucces(company);
	}
	

}
