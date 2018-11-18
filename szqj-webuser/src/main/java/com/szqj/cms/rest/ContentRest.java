package com.szqj.cms.rest;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.cms.domain.ColumnInfoRepository;
import com.szqj.cms.domain.ContentInfo;
import com.szqj.cms.domain.ContentInfoRepository;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.service.AccountService;



@RestController
@RequestMapping("/system/content/")
@EnableAutoConfiguration
public class ContentRest {


	@Autowired
	private  ContentInfoRepository  contentInfoRepository;
	
	
	
	//根据栏目返回内容列表
	@RequestMapping(value = "list.do"  )
	public RestJson list( String columnId,   String contentTitle,   Integer pageNum, Integer size){
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		Page<ContentInfo> page=null;
		if(StringUtils.isBlank(contentTitle)){
			page = contentInfoRepository.findByColumnId(columnId, pageable);
		}else{
			page =contentInfoRepository.findByColumnId(columnId, contentTitle, pageable);
		}
		return RestJson.createSucces(page);
	}
	
	
	//保存账号信息
	@RequestMapping(value = "save.do"  )
	public RestJson save( ContentInfo contentInfo){
		contentInfoRepository.save(contentInfo);
		return RestJson.createSucces(contentInfo);
	}
	
	//更新
	@RequestMapping(value = "update.do"  )
	public RestJson update( ContentInfo contentInfo){
		contentInfoRepository.save(contentInfo);
		return RestJson.createSucces(contentInfo);
	}
	
	//删除
	@RequestMapping(value = "delete.do"  )
	public RestJson delete( String contentId){
		contentInfoRepository.deleteById(contentId);
		return RestJson.createSucces();
	}
	
	
	
	
		
	
	
	

}
