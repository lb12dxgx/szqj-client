package com.szqj.company.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.company.domain.Company;
import com.szqj.company.domain.CompanyRepository;
import com.szqj.company.service.CompanyService;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.service.AccountService;



@RestController
@RequestMapping("/admin/company/")
@EnableAutoConfiguration
public class CompanyRest {
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	
	//根据账号类型返回账号列表
	@RequestMapping(value = "list.do"  )
	
	public RestJson list(String name,   Integer pageNum, Integer size){
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		Page<Company> page = companyRepository.findPageByName(name, pageable);
		return RestJson.createSucces(page);
	}
	
	

		
	
	
	

}
