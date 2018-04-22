package com.szqj.reg.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.company.domain.Company;
import com.szqj.company.service.CompanyService;
import com.szqj.reg.domain.RegInfo;
import com.szqj.reg.service.RegService;
import com.szqj.util.RestJson;

@RestController
@RequestMapping("/reg/person/")
@EnableAutoConfiguration
public class PersonRegRest {
	
	
	@Autowired
	private RegService regService;
	
	
	/*@RequestMapping(value = "reg.do"  )
	public RestJson reg(RegInfo regInfo){
		regService.regPerson(regInfo);
		return RestJson.createSucces(regInfo);
	}*/
	

	/*@RequestMapping(value = "getInviteCode.do"  )
	public RestJson getInviteCode(){
		String inviteCode = regService.genInviteCode(4);
		return RestJson.createSucces(inviteCode);
	}*/
	

}
