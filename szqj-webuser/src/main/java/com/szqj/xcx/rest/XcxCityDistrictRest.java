package com.szqj.xcx.rest;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.before.domain.CityAreaRepository;
import com.szqj.before.domain.CityDistrict;
import com.szqj.before.domain.CityDistrictRepository;
import com.szqj.before.domain.PersonArea;
import com.szqj.before.domain.PersonAreaRepository;
import com.szqj.reg.service.RegService;
import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.EnterpriseRepository;
import com.szqj.service.domain.Person;
import com.szqj.util.RestJson;

@RestController
@RequestMapping("/xcx/login/")
@EnableAutoConfiguration
public class XcxCityDistrictRest {
	
	@Autowired
	private CityDistrictRepository cityDistrictRepository;
	
	@Autowired
	private PersonAreaRepository personAreaRepository;
	
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	
	@Autowired
	private RegService regService;
	

	@RequestMapping(value = "/before/citydistrict/save.xcx"  )
	public RestJson save( @ModelAttribute("openid") String openid,String[] cityDistrictIdList){
		Person person = regService.getPersonByOpenid(openid);
		String enterpriseId=person.getCompanyId();
		PersonArea personArea=new PersonArea();
		personArea.setEnterpriseId(enterpriseId);
		personArea.setOpenid(openid);
		personArea.setType(1);
		String content="";
		for(String cityDistrictId:cityDistrictIdList ){
			if(StringUtils.isBlank(cityDistrictId)){
				content="1,"+cityDistrictId;
			}else{
				content=content+","+cityDistrictId;
			}
		}
		personArea.setContent(content);
		
		personAreaRepository.save(personArea);
		
		return RestJson.createSucces();
	}
	
	@RequestMapping(value = "/before/citydistrict/getDistrictByOpenId.xcx"  )
	public RestJson getDistrictByOpenId( @ModelAttribute("openid") String openid){
		Person person = regService.getPersonByOpenid(openid);
		String enterpriseId=person.getCompanyId();
		Enterprise enterprise = enterpriseRepository.findById(enterpriseId).get();
		List<CityDistrict> list = cityDistrictRepository.findByApplyCityId(enterprise.getApplyCityId());
		return RestJson.createSucces(list);
	}
	
	
	

}
