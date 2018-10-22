package com.szqj.xcx.rest;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.before.domain.CityArea;
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
public class XcxCityAreaRest {
	
	@Autowired
	private CityDistrictRepository cityDistrictRepository;
	
	@Autowired
	private CityAreaRepository cityAreaRepository;
	
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	
	@Autowired
	private PersonAreaRepository personAreaRepository;
	
	@Autowired
	private RegService regService;
	
	
	
	@RequestMapping(value = "/before/cityarea/save.xcx"  )
	public RestJson save( @ModelAttribute("openid") String openid,String[] cityAreaIdList){
		Person person = regService.getPersonByOpenid(openid);
		String enterpriseId=person.getCompanyId();
		 List<PersonArea> personAreas = personAreaRepository.findByOpenidAndEnterpriseId(openid,enterpriseId);
		 PersonArea personArea=new PersonArea();
		if(personAreas!=null&&personAreas.size()>0){
			personArea=personAreas.get(0);
		}
		personArea.setEnterpriseId(enterpriseId);
		personArea.setOpenid(openid);
		personArea.setPersonId(person.getPersonId());
		personArea.setType(2);
		String content="";
		for(String cityAreaId:cityAreaIdList ){
			if(StringUtils.isBlank(content)){
				content="1,"+cityAreaId;
			}else{
				content=content+","+cityAreaId;
			}
		}
		personArea.setContent(content);
		
		personAreaRepository.save(personArea);
		
		return RestJson.createSucces();
	}
	
	@RequestMapping(value = "/before/cityarea/getAreaByOpenId.xcx"  )
	public RestJson getAreaByOpenId(@ModelAttribute("openid") String openid){
		Person person = regService.getPersonByOpenid(openid);
		String enterpriseId=person.getCompanyId();
		Enterprise enterprise = enterpriseRepository.findById(enterpriseId).get();
		String applyCityId=enterprise.getApplyCityId();
		List<CityDistrict> list = cityDistrictRepository.findByApplyCityId(applyCityId);
		String personAreaStr="";
		List<PersonArea> personAreaList = personAreaRepository.findByOpenidAndEnterpriseId(openid,enterpriseId);
		if(personAreaList!=null&&personAreaList.size()>0){
			personAreaStr=personAreaList.get(0).getContent();
		}
		
		for(CityDistrict cityDistrict:list){
			String cityDistrictId=cityDistrict.getCityDistrictId();
			List<CityArea> areaList = cityAreaRepository.findByCityDistrictId(cityDistrictId);
			for(CityArea cityArea:areaList){
				String cityAreaId = cityArea.getCityAreaId();
				if(personAreaStr.indexOf(cityAreaId)>0){
					cityArea.setChecked(true);
				}
			}
			cityDistrict.setList(areaList);
		}
		
		return RestJson.createSucces(list);
	}
	
	
	

	
	
	
	
	
	
	
	

}
