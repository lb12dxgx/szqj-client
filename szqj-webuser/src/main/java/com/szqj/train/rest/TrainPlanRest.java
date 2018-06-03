package com.szqj.train.rest;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.service.domain.Meet;
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
	
	public RestJson list( String trainName,   Integer pageNum, Integer size){
		Page<TrainPlan> page=null;
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		if(StringUtils.isNotBlank(trainName)) {
			page = trainPlanRepository.findPageByTrainName(trainName, pageable);
		}else {
			page = trainPlanRepository.findPage(pageable);
		}
		return RestJson.createSucces(page);
	}
	
	@RequestMapping(value = "get.do"  )
	public RestJson get(String trainPlanId){
		TrainPlan trainPlan = trainPlanRepository.findById(trainPlanId).get();
		return RestJson.createSucces(trainPlan);
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
	
	@RequestMapping(value = "changeSign.do"  )
	public RestJson changeSign(String trainPlanId){
		TrainPlan trainPlan = trainPlanRepository.findById(trainPlanId).get();
		if(trainPlan.getIsSign()==1){
			trainPlan.setIsSign(0);
		}else{
			trainPlan.setIsSign(1);
		}
		trainPlanRepository.save(trainPlan);
		return RestJson.createSucces();
	}
	
		
	
	
		
	
	
	

}
