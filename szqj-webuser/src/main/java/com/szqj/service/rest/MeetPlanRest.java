package com.szqj.service.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.service.domain.MeetPlan;
import com.szqj.service.domain.MeetPlanRepository;
import com.szqj.util.RestJson;



@RestController
@RequestMapping("/system/meetplan/")
@EnableAutoConfiguration
public class MeetPlanRest {
	
	@Autowired
	private MeetPlanRepository meetPlanRepository;
	
	
	
	@RequestMapping(value = "list.do"  )
	
	public RestJson list( String meetId,   Integer pageNum, Integer size){
	
		List<MeetPlan> list = meetPlanRepository.findByMeetId(meetId);
		return RestJson.createSucces(list);
	}
	
	@RequestMapping(value = "get.do"  )
	public RestJson get(String meetPlanId){
		MeetPlan meetPlan = meetPlanRepository.findById(meetPlanId).get();
		return RestJson.createSucces(meetPlan);
	}
	
	@RequestMapping(value = "save.do"  )
	public RestJson save( MeetPlan meetPlan){
		meetPlan.setCreateDate(new Date());
		meetPlanRepository.save(meetPlan);
		return RestJson.createSucces(meetPlan);
	}
	
	
	@RequestMapping(value = "update.do"  )
	public RestJson update( MeetPlan meetPlan){
		meetPlanRepository.save(meetPlan);
		return RestJson.createSucces(meetPlan);
	}
	
	
	@RequestMapping(value = "delete.do"  )
	public RestJson delete( String meetPlanId){
		meetPlanRepository.deleteById(meetPlanId);
		return RestJson.createSucces();
	}
	
	
		
	
	
	

}
