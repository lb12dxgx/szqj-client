package com.szqj.xcx.rest;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.service.domain.AccidentInfo;
import com.szqj.service.domain.Meet;
import com.szqj.service.domain.MeetPlan;
import com.szqj.service.domain.MeetPlanRepository;
import com.szqj.service.domain.MeetRepository;
import com.szqj.service.domain.Person;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;

@RestController
@RequestMapping("/xcx/login/")
@EnableAutoConfiguration
public class XcxMeetRest {
	
	@Autowired
	private MeetRepository meetRepository;
	
	@Autowired
	private MeetPlanRepository meetPlanRepository;
	
	
	@RequestMapping(value = "/meet/list.xcx"  )
	public RestJson list(Integer pageNum, Integer size){
		PageRequest pageable=Tools.getPage(pageNum,5);
		Page<Meet> page=null;
		page=meetRepository.findPage(pageable);
		return RestJson.createSucces(page);
	}
	
	
	@RequestMapping(value = "/meet/get.xcx"  )
	public RestJson get(String meetId){
		Meet meet = meetRepository.findById(meetId).get();
		return RestJson.createSucces(meet);
		
	}
	
	@RequestMapping(value = "/meet/plan/list.xcx"  )
	public RestJson planList(String meetId){
		 List<MeetPlan> list = meetPlanRepository.findByMeetId(meetId);
		return RestJson.createSucces(list);
		
	}
	
	
	
	@RequestMapping(value = "/meet/plan/get.xcx"  )
	public RestJson getPlan(String meetPlanId){
		 MeetPlan meetPlan = meetPlanRepository.findById(meetPlanId).get();
		return RestJson.createSucces(meetPlan);
		
	}
	
	

}
