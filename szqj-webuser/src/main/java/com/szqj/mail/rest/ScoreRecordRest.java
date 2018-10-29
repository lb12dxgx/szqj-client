package com.szqj.mail.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;

import com.szqj.mail.domain.ScoreRecord;
import com.szqj.mail.domain.ScoreRecordRepository;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;

public class ScoreRecordRest {
	
	@Autowired
	private ScoreRecordRepository scoreRecordRepository;
	
	
	@RequestMapping(value = "list.do"  )
	public RestJson list(String personName, Integer pageNum, Integer size){
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		Page<ScoreRecord> page=null;
		if(StringUtils.isNotBlank(personName)){
			page=scoreRecordRepository.findByPersonName(personName,pageable);
		}else{
			page=scoreRecordRepository.findAll(pageable);
		}
		
		return RestJson.createSucces(page);
	}
	
	
	
	

}
