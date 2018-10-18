package com.szqj.before.rest;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.before.domain.ApplyCity;
import com.szqj.before.domain.ApplyCityRepository;
import com.szqj.before.domain.ApplyOrg;
import com.szqj.before.domain.ApplyOrgRepository;
import com.szqj.before.domain.CityDistrict;
import com.szqj.before.domain.CityDistrictRepository;
import com.szqj.before.service.ApplyOrgService;
import com.szqj.util.ConstantUtils;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;

@RestController
@RequestMapping("/system/citydistrict")
@EnableAutoConfiguration
public class CityDistrictRest {
	
	@Autowired
	private CityDistrictRepository cityDistrictRepository;
	
	@Autowired
	private ApplyCityRepository applyCityRepository;
	
	
	
	
	@RequestMapping(value = "list.do"  )
	public RestJson list(String applyCityId){
		List<CityDistrict> list = cityDistrictRepository.findAllByApplyCityId(applyCityId);
		return RestJson.createSucces(list);
	}
	
	
	
	@RequestMapping(value = "save.do")
	public RestJson save(CityDistrict cityDistrict,String applyCityId){
		ApplyCity applyCity = applyCityRepository.getOne(applyCityId);
		cityDistrict.setApplyCity(applyCity);
		cityDistrict.setState(0);
		cityDistrictRepository.save(cityDistrict);
		return RestJson.createSucces();
	}
	
	@RequestMapping(value = "disabled.do"  )
	public RestJson disabled(String cityDistrictId,Integer state){
		CityDistrict cityDistrict = cityDistrictRepository.findById(cityDistrictId).get();
		cityDistrict.setState(1);
		cityDistrictRepository.save(cityDistrict);
		return RestJson.createSucces();
	}
	
	
	@RequestMapping(value = "enabled.do"  )
	public RestJson enabled(String cityDistrictId,Integer state){
		CityDistrict cityDistrict = cityDistrictRepository.findById(cityDistrictId).get();
		cityDistrict.setState(0);
		cityDistrictRepository.save(cityDistrict);
		return RestJson.createSucces();
	}
	
	
	
	
	
	
	
	

}
