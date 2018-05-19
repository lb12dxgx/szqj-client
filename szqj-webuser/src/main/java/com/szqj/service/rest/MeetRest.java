package com.szqj.service.rest;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.service.domain.Meet;
import com.szqj.service.domain.MeetRepository;
import com.szqj.service.domain.Product;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;



@RestController
@RequestMapping("/system/meet/")
@EnableAutoConfiguration
public class MeetRest {
	
	@Autowired
	private MeetRepository meetRepository;
	
	

	@RequestMapping(value = "list.do"  )
	public RestJson list( String meetName,   Integer pageNum, Integer size){
		
		Page<Meet> page=null;
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		if(StringUtils.isNotBlank(meetName)) {
			page =  meetRepository.findPageByMeetName(meetName, pageable);
		}else {
			page = meetRepository.findPage(pageable);
		}
		
		return RestJson.createSucces(page);
	}
	
	@RequestMapping(value = "get.do"  )
	public RestJson get(String meetId){
		Meet meet = meetRepository.findById(meetId).get();
		return RestJson.createSucces(meet);
	}
	
	
	@RequestMapping(value = "save.do"  )
	public RestJson save( Meet meet){
		meet.setCreateDate(new Date());
		meetRepository.save(meet);
		return RestJson.createSucces(meet);
	}
	
	
	@RequestMapping(value = "update.do"  )
	public RestJson update( Meet meet){
		meetRepository.save(meet);
		return RestJson.createSucces(meet);
	}
	
	
	@RequestMapping(value = "delete.do"  )
	public RestJson delete( String meetId){
		meetRepository.deleteById(meetId);
		return RestJson.createSucces();
	}
	
	
		
	
	
	

}
