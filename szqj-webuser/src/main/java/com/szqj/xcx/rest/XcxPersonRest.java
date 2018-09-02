package com.szqj.xcx.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.szqj.reg.service.RegService;
import com.szqj.service.domain.MyPersonRepository;
import com.szqj.service.domain.Person;
import com.szqj.util.RestJson;

@RestController
@RequestMapping("/xcx/")
@EnableAutoConfiguration
public class XcxPersonRest {
	
	@Autowired
	private RegService regService;
	@Autowired 
	private MyPersonRepository personRepository;
	
  
	
	
	@RequestMapping(value = "isExitByUserCode.xcx"  )
	public RestJson isExitByUserCode(String userCode){
		boolean flag = regService.isExitByUserCode(userCode);
		return RestJson.createSucces(flag);
	}
	
	@RequestMapping(value = "savePersonDesc.xcx"  )
	public RestJson savePersonDesc(String openid,String personSex,String enterpriseName,String userCode,String personName) {
		Person person = regService.getPersonByOpenid(openid);
		person.setPersonSex(personSex);
		person.setPersonName(personName);
		person.setEnterpriseName(enterpriseName);
		person.setUserCode(userCode);
		personRepository.save(person);
		return RestJson.createSucces(person);
	
	}
	
	@RequestMapping(value = "getPersonByOpenid.xcx"  )
	public RestJson savePersonDesc(String openid) {
		Person person = regService.getPersonByOpenid(openid);
		return RestJson.createSucces(person);
	
	}
	

}
