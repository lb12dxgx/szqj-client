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
import com.szqj.before.domain.CityArea;
import com.szqj.before.domain.CityAreaRepository;
import com.szqj.before.domain.CityDistrict;
import com.szqj.before.domain.CityDistrictRepository;
import com.szqj.before.domain.PersonArea;
import com.szqj.before.domain.PersonAreaRepository;
import com.szqj.before.service.ApplyOrgService;
import com.szqj.util.ConstantUtils;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;

@RestController
@RequestMapping("/system/cityarea")
@EnableAutoConfiguration
public class CityAreaRest {
	
	@Autowired
	private CityAreaRepository cityAreaRepository;
	@Autowired
	private CityDistrictRepository cityDistrictRepository;
	
	
	
	
	@RequestMapping(value = "list.do"  )
	public RestJson list(String cityDistrictId){
		List<CityArea> list = cityAreaRepository.findAllByCityDistrictId(cityDistrictId);
		return RestJson.createSucces(list);
	}
	
	
	
	@RequestMapping(value = "save.do")
	public RestJson save(CityArea cityArea,String cityDistrictId){
		CityDistrict cityDistrict = cityDistrictRepository.getOne(cityDistrictId);
		cityArea.setApplyCity(cityDistrict.getApplyCity());
		cityArea.setCityDistrict(cityDistrict);
		cityArea.setState(0);
		cityAreaRepository.save(cityArea);
		return RestJson.createSucces();
	}
	
	@RequestMapping(value = "disabled.do"  )
	public RestJson disabled(String cityAreaId){
		CityArea cityArea = cityAreaRepository.findById(cityAreaId).get();
		cityArea.setState(1);
		cityAreaRepository.save(cityArea);
		return RestJson.createSucces();
	}
	
	
	@RequestMapping(value = "enabled.do"  )
	public RestJson enabled(String cityAreaId){
		CityArea cityArea = cityAreaRepository.findById(cityAreaId).get();
		cityArea.setState(0);
		cityAreaRepository.save(cityArea);
		return RestJson.createSucces();
	}
	
	
	
	
	
	
	
	

}
