package com.szqj.train.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.train.domain.TrainPlan;
import com.szqj.train.domain.TrainPlanRepository;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;



@RestController
@RequestMapping("/system/trainplan/")
@EnableAutoConfiguration
public class TrainPlanRest {
	
	@Autowired
	private TrainPlanRepository trainPlanRepository;
	
	
	
	@RequestMapping(value = "list.do"  )
	
	public RestJson list( String trainPlanName,   Integer pageNum, Integer size){
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		Page<TrainPlan> page = trainPlanRepository.findPageByTrainPlanName(trainPlanName, pageable);
		return RestJson.createSucces(page);
	}
	
	
	
	@RequestMapping(value = "save.do"  )
	public RestJson save( TrainPlan trainPlan){
		trainPlan.setCreateDate(new Date());
		trainPlanRepository.save(trainPlan);
		return RestJson.createSucces(trainPlan);
	}
	
	
	@RequestMapping(value = "update.do"  )
	public RestJson update( TrainPlan trainPlan){
		trainPlanRepository.save(trainPlan);
		return RestJson.createSucces(trainPlan);
	}
	
	
	@RequestMapping(value = "delete.do"  )
	public RestJson delete( String trainPlanId){
		trainPlanRepository.deleteById(trainPlanId);
		return RestJson.createSucces();
	}
	
	
		
	
	
	

}
