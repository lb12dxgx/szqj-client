package com.szqj.cms.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.cms.domain.OutKeyInfo;
import com.szqj.cms.domain.OutKeyInfoRepository;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.service.AccountService;



@RestController
@RequestMapping("/system/outkey/")
@EnableAutoConfiguration
public class OutKeyRest {
	
	@Autowired
	private OutKeyInfoRepository outKeyInfoRepository;
	
	
	//根据账号类型返回账号列表
	@RequestMapping(value = "list.do"  )
	public RestJson list(){
		List<OutKeyInfo> l = outKeyInfoRepository.findAll();
		return RestJson.createSucces(l);
	}
	
	
	//保存账号信息
	@RequestMapping(value = "save.do"  )
	public RestJson save( OutKeyInfo outKeyInfo){
		outKeyInfoRepository.save(outKeyInfo);
		return RestJson.createSucces(outKeyInfo);
	}
	
	
	
	//删除
	@RequestMapping(value = "delete.do"  )
	public RestJson delete( String outkeyinfoId){
		outKeyInfoRepository.deleteById(outkeyinfoId);
		return RestJson.createSucces();
	}
	
	
	
	
	

}
