package com.szqj.train.rest;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.train.domain.TrainCert;
import com.szqj.train.domain.TrainCertRepository;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;



@RestController
@RequestMapping("/system/traincert/")
@EnableAutoConfiguration
public class TrainCertRest {
	
	@Autowired
	private TrainCertRepository trainCertRepository;
	
	
	
	@RequestMapping(value = "list.do"  )
	
	public RestJson list( String trainPlanId, String userName, Integer pageNum, Integer size){
	
		Page<TrainCert> page=null;
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		if(StringUtils.isNotBlank(userName)) {
			page = trainCertRepository.findByTrainPlanIdAndUserName(trainPlanId, userName, pageable);
		}else {
			page = trainCertRepository.findByTrainPlanId(trainPlanId, pageable);
		}
		return RestJson.createSucces(page);
		
		
	}
	
	@RequestMapping(value = "get.do"  )
	public RestJson get(String trainCertId){
		TrainCert trainCert = trainCertRepository.findById(trainCertId).get();
		return RestJson.createSucces(trainCert);
	}
	
	@RequestMapping(value = "save.do"  )
	public RestJson save( TrainCert trainCert){
		trainCert.setCreateDate(new Date());
		trainCertRepository.save(trainCert);
		return RestJson.createSucces(trainCert);
	}
	
	
	@RequestMapping(value = "update.do"  )
	public RestJson update( TrainCert trainCert){
		trainCertRepository.save(trainCert);
		return RestJson.createSucces(trainCert);
	}
	
	
	@RequestMapping(value = "delete.do"  )
	public RestJson delete( String trainCertId){
		trainCertRepository.deleteById(trainCertId);
		return RestJson.createSucces();
	}
	
	
		
	
	
	

}
