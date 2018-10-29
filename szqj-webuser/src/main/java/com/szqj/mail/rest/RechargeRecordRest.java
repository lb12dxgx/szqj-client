package com.szqj.mail.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;

import com.szqj.mail.domain.RechargeRecord;
import com.szqj.mail.domain.RechargeRecordRepository;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;

public class RechargeRecordRest {
	
	@Autowired
	private RechargeRecordRepository rechargeRecordRepository;
	
	
	@RequestMapping(value = "list.do"  )
	public RestJson list(String personName, Integer pageNum, Integer size){
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		Page<RechargeRecord> page=null;
		if(StringUtils.isNotBlank(personName)){
			page=rechargeRecordRepository.findByPersonName(personName,pageable);
		}else{
			page=rechargeRecordRepository.findAll(pageable);
		}
		
		return RestJson.createSucces(page);
	}
	
	
	
	

}
