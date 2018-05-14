package com.szqj.service.rest;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.service.domain.ZbInfo;
import com.szqj.service.domain.ZbInfoRepository;
import com.szqj.train.domain.TrainPlan;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;



@RestController
@RequestMapping("/system/zbinfo/")
@EnableAutoConfiguration
public class  ZbInfoRest {
	
	@Autowired
	private ZbInfoRepository zbInfoRepository;
	
	
	
	@RequestMapping(value = "list.do"  )
	public RestJson list( String zbXmName,   Integer pageNum, Integer size){
		Page<ZbInfo> page=null;
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		if(StringUtils.isNotBlank(zbXmName)) {
			page = zbInfoRepository.findPageByZbXmName(zbXmName, pageable);
		}else {
			page = zbInfoRepository.findPage(pageable);
		}
		return RestJson.createSucces(page);
	}
	
	
	
	@RequestMapping(value = "save.do"  )
	public RestJson save( ZbInfo zbInfo){
		zbInfo.setCreateDate(new Date());
		zbInfoRepository.save(zbInfo);
		return RestJson.createSucces(zbInfo);
	}
	
	
	@RequestMapping(value = "get.do"  )
	public RestJson get(String zbInfoId){
		ZbInfo zbInfo = zbInfoRepository.findById(zbInfoId).get();
		return RestJson.createSucces(zbInfo);
	}
	
	
	@RequestMapping(value = "update.do"  )
	public RestJson update( ZbInfo zbInfo){
		zbInfoRepository.save(zbInfo);
		return RestJson.createSucces(zbInfo);
	}
	
	
	@RequestMapping(value = "delete.do"  )
	public RestJson delete( String zbInfoId){
		zbInfoRepository.deleteById(zbInfoId);
		return RestJson.createSucces();
	}
	
	
		
	
	
	

}
