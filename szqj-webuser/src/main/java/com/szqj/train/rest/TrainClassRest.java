package com.szqj.train.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.train.domain.TrainClass;
import com.szqj.train.domain.TrainClassRepository;
import com.szqj.train.domain.TrainPlan;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;



@RestController
@RequestMapping("/system/trainclass/")
@EnableAutoConfiguration
public class TrainClassRest {
	
	@Autowired
	private TrainClassRepository trainClassRepository;
	
	
	
	@RequestMapping(value = "list.do"  )
	
	public RestJson list( String trainPlanId,   Integer pageNum, Integer size){
	
		List<TrainClass> list = trainClassRepository.findByTrainPlanId(trainPlanId);
		return RestJson.createSucces(list);
	}
	
	@RequestMapping(value = "get.do"  )
	public RestJson get(String trainClassId){
		TrainClass trainClass = trainClassRepository.findById(trainClassId).get();
		return RestJson.createSucces(trainClass);
	}
	
	@RequestMapping(value = "save.do"  )
	public RestJson save( TrainClass trainClass){
		trainClass.setCreateDate(new Date());
		trainClassRepository.save(trainClass);
		return RestJson.createSucces(trainClass);
	}
	
	
	@RequestMapping(value = "update.do"  )
	public RestJson update( TrainClass trainClass){
		trainClassRepository.save(trainClass);
		return RestJson.createSucces(trainClass);
	}
	
	
	@RequestMapping(value = "delete.do"  )
	public RestJson delete( String trainClassId){
		trainClassRepository.deleteById(trainClassId);
		return RestJson.createSucces();
	}
	
	
		
	
	
	

}
