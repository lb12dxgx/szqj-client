package com.szqj.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szqj.company.domain.Company;
import com.szqj.company.domain.CompanyRepository;

/**
 * 
 * @ClassName: AccountService
 * @Description: 账号登录类
 * @author zhanggy
 * 
 *
 */
@Service
@Transactional
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	public void reg(Company company) {
		
		
	}
	
	

	


	
	
	

}
