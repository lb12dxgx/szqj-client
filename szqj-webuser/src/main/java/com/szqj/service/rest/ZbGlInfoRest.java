package com.szqj.service.rest;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.service.domain.ZbGlInfo;
import com.szqj.service.domain.ZbGlInfoRepository;
import com.szqj.service.domain.ZbInfo;
import com.szqj.service.domain.ZbInfoRepository;
import com.szqj.train.domain.TrainPlan;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;



@RestController
@RequestMapping("/system/zbglinfo/")
@EnableAutoConfiguration
public class  ZbGlInfoRest {
	
	@Autowired
	private ZbGlInfoRepository zbInfoRepository;
	
	
	
	@RequestMapping(value = "list.do"  )
	public RestJson list( String zbXmName,   Integer pageNum, Integer size){
		Page<ZbGlInfo> page=null;
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		if(StringUtils.isNotBlank(zbXmName)) {
			page = zbInfoRepository.findPageByZbXmName(zbXmName, pageable);
		}else {
			page = zbInfoRepository.findPage(pageable);
		}
	
		List<ZbGlInfo> l = page.getContent();
		for(ZbGlInfo zb:l){
			zb.setZbContent("");
		}
		
		return RestJson.createSucces(page);
	}
	
	
	
	@RequestMapping(value = "save.do"  )
	public RestJson save( ZbGlInfo zbInfo){
		zbInfo.setCreateDate(new Date());
		zbInfoRepository.save(zbInfo);
		return RestJson.createSucces(zbInfo);
	}
	
	
	@RequestMapping(value = "get.do"  )
	public RestJson get(String zbInfoId){
		ZbGlInfo zbInfo = zbInfoRepository.findById(zbInfoId).get();
		return RestJson.createSucces(zbInfo);
	}
	
	
	@RequestMapping(value = "update.do"  )
	public RestJson update( ZbGlInfo zbInfo){
		zbInfoRepository.save(zbInfo);
		return RestJson.createSucces(zbInfo);
	}
	
	
	@RequestMapping(value = "delete.do"  )
	public RestJson delete( String zbInfoId){
		zbInfoRepository.deleteById(zbInfoId);
		return RestJson.createSucces();
	}
	
	
		
	
	
	

}
