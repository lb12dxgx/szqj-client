package com.szqj.client.org;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.szqj.client.org.bean.CompanyBean;
import com.szqj.client.org.bean.CustomerCompanyBean;
import com.szqj.client.org.bean.RegInfo;




public interface OrgClient {

	@RequestMapping(value = "/client/customerCompany/get.do")
	public CustomerCompanyBean get(@RequestParam(value = "customerCompanyId")String customerCompanyId);
	
	
	@RequestMapping(value = "/client/reg/regByCompany.do")
	public String regByCompany(@RequestBody RegInfo reg);
	
	@RequestMapping(value = "/client/company/list.do")
	public List<CompanyBean> CompanyBeanList();

}
