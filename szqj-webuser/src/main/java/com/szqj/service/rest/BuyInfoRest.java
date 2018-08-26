package com.szqj.service.rest;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.service.domain.AbilityInfo;
import com.szqj.service.domain.AbilityInfoRepository;
import com.szqj.service.domain.BuyInfo;
import com.szqj.service.domain.BuyInfoRepository;
import com.szqj.service.domain.JobInfo;
import com.szqj.service.domain.JobInfoRepository;
import com.szqj.service.domain.Meet;
import com.szqj.service.domain.MeetRepository;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;

@RestController
@RequestMapping("/system/buyinfo/")
@EnableAutoConfiguration
public class BuyInfoRest {
	
	@Autowired
	private BuyInfoRepository buyInfoRepository;
	
	

	@RequestMapping(value = "list.do"  )
	public RestJson list(String empName, Integer pageNum, Integer size){
		
		Page<BuyInfo> page=null;
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		if(StringUtils.isNotBlank(empName)){
			page =  buyInfoRepository.findPageByEmpName(empName, pageable);
		}else {
			page =buyInfoRepository.findPage(pageable);
		}
		
		return RestJson.createSucces(page);
	}
	
	
	@RequestMapping(value = "delete.do"  )
	public RestJson delete(String buyInfoId, Integer size){
		buyInfoRepository.deleteById(buyInfoId);
		return RestJson.createSucces();
	}
	
	
	

}
