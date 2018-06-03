package com.szqj.weixin.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.train.domain.TrainPlan;
import com.szqj.train.domain.TrainPlanRepository;
import com.szqj.train.domain.TrainSignUp;
import com.szqj.train.domain.TrainSignUpRepository;
import com.szqj.util.RestJson;

@RestController
@RequestMapping("/weixin/")
@EnableAutoConfiguration
public class TrainSignRest {
	
	@Autowired
	private TrainSignUpRepository trainSignUpRepository;
	
	@Autowired
	private TrainPlanRepository trainPlanRepository;
	
	
	@RequestMapping(value = "getTrainPlan.wei"  )
	public RestJson get(){
		 List<TrainPlan> list = trainPlanRepository.findByIsSign();
		 if(list==null||list.size()==0) {
			 return RestJson.createError();
		 }
		return RestJson.createSucces(list.get(0));
	}
	
	@RequestMapping(value = "signTrainPlan.wei"  )
	public RestJson sign(String trainPlanId,String telphone) {
		List<TrainSignUp> l = trainSignUpRepository.findListByTrainPlanIdAndTelphone(trainPlanId, telphone);
		if(l==null||l.size()==0) {
			 return RestJson.createError("手机号未参与报名,请重新输入!");
		 }
		TrainSignUp trainSignUp=l.get(0);
		if(trainSignUp.getIsSign()==1) {
			return RestJson.createError(trainSignUp.getUserName()+"已经签到,请勿重复签到!");
		}
		trainSignUp.setIsSign(1);
		trainSignUpRepository.save(trainSignUp);
		return RestJson.createSucces(trainSignUp);
	}

}
