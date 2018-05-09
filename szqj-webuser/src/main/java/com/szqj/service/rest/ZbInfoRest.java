package com.szqj.service.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.cms.domain.ColumnInfoRepository;
import com.szqj.service.domain.ZbInfo;
import com.szqj.service.domain.ZbInfoRepository;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.service.AccountService;



@RestController
@RequestMapping("/system/zbInfo/")
@EnableAutoConfiguration
public class  ZbInfoRest {
	
	@Autowired
	private ZbInfoRepository zbInfoRepository;
	
	
	
	@RequestMapping(value = "list.do"  )
	public RestJson list( String zbXmName,   Integer pageNum, Integer size){
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		Page<ZbInfo> page = zbInfoRepository.findPageByZbXmName(zbXmName, pageable);
		return RestJson.createSucces(page);
	}
	
	
	
	@RequestMapping(value = "save.do"  )
	public RestJson save( ZbInfo zbInfo){
		zbInfo.setCreateDate(new Date());
		zbInfoRepository.save(zbInfo);
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
