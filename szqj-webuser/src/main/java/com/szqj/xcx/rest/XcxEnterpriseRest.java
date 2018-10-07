package com.szqj.xcx.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.reg.service.RegService;
import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.EnterpriseRepository;
import com.szqj.service.domain.MyPersonRepository;
import com.szqj.service.domain.Person;
import com.szqj.util.RestJson;

@RestController
@RequestMapping("/xcx/login/")
@EnableAutoConfiguration
public class XcxEnterpriseRest {
	
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	
	@Autowired
	private RegService regService;
	
	@Autowired 
	private MyPersonRepository personRepository;
	
	@RequestMapping(value = "/before/enterprise/exitByNameAndCity.xcx"  )
	public RestJson exitByNameAndCity(String applyCityId,String enterpriseType,String enterpriseName ){
		List<Enterprise> l = enterpriseRepository.findByApplyCityIdAndEnterpriseTypeAndName(applyCityId,enterpriseName,enterpriseType);
		if(l==null||l.size()==0) {
			return RestJson.createSucces(true);
		}
		return RestJson.createSucces(false);
	}
	
	
	@RequestMapping(value = "/before/enterprise/list.xcx"  )
	public RestJson list(String applyCityId,Integer enterpriseType ){
		List<Enterprise> l = enterpriseRepository.findByApplyCityIdAndEnterpriseType(applyCityId,enterpriseType);
		return RestJson.createSucces(l);
	}
	
	@RequestMapping(value = "/before/enterprise/save.xcx"  )
	public RestJson list(Enterprise enterprise){
		enterpriseRepository.save(enterprise);
		return RestJson.createSucces(enterprise);
	}
	
	@RequestMapping(value = "/before/enterprise/addPerson.xcx"  )
	public RestJson addPerson(@ModelAttribute("openid") String openid,String enterpriseId){
		Person person = regService.getPersonByOpenid(openid);
		person.setCompanyId(enterpriseId);
		personRepository.save(person);
		return RestJson.createSucces(person);
	}

}
