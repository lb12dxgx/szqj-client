package com.szqj.weixin.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.service.domain.Meet;
import com.szqj.service.domain.MeetRepository;
import com.szqj.service.domain.MeetSignUp;
import com.szqj.service.domain.MeetSignUpRepository;
import com.szqj.util.RestJson;

@RestController
@RequestMapping("/weixin/")
@EnableAutoConfiguration
public class MeetSignRest {
	
	@Autowired
	private MeetSignUpRepository meetsignRepository;
	
	@Autowired
	private MeetRepository meetRepository;
	
	
	@RequestMapping(value = "getMeet.wei"  )
	public RestJson get(){
		 List<Meet> meets = meetRepository.findByIsSign();
		 if(meets==null||meets.size()==0) {
			 return RestJson.createError();
		 }
		return RestJson.createSucces(meets.get(0));
	}
	
	@RequestMapping(value = "getSignMeet.wei"  )
	public RestJson getSign(String meetId,String userName) {
		List<MeetSignUp> l = meetsignRepository.findListByMeetIdAndUserName(meetId, userName);
		if(l==null||l.size()==0) {
			 return RestJson.createError(userName+"未参与报名,请重新输入!");
		 }
		return RestJson.createSucces(l);
	}
	
	
	@RequestMapping(value = "signMeet.wei"  )
	public RestJson sign(String meetSignUpId) {
		MeetSignUp meetSignUp = meetsignRepository.findById(meetSignUpId).get();
		
		if(meetSignUp.getIsSign()==1) {
			return RestJson.createError(meetSignUp.getUserName()+"已经签到,请勿重复签到!");
		}
		meetSignUp.setIsSign(1);
		meetsignRepository.save(meetSignUp);
		return RestJson.createSucces(meetSignUp);
	}

}
