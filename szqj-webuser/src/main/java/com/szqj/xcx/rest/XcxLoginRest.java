package com.szqj.xcx.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.szqj.redis.RedisService;
import com.szqj.reg.domain.RegInfo;
import com.szqj.reg.service.RegService;
import com.szqj.service.domain.Person;
import com.szqj.util.RestJson;

@RestController
@RequestMapping("/xcx/")
@EnableAutoConfiguration
public class XcxLoginRest {
	@Autowired
	private RegService regService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private  RedisService redisService;
	 
	
	
	
	@RequestMapping(value = "isExitByTelphone.xcx"  )
	public RestJson isExitByTelphone(String telphone, Integer type){
		RegInfo regInfo=new RegInfo();
		regInfo.setTelphone(telphone);
		regInfo.setType(type);
		boolean flag = regService.isExitByTelphone(regInfo);
		return RestJson.createSucces(flag);
	}
	
	
	@RequestMapping(value = "getSmsCode.xcx"  )
	public RestJson getSmsCode(String telphone, Integer type,String openid){
		RegInfo regInfo=new RegInfo();
		regInfo.setTelphone(telphone);
		regInfo.setType(type);
		String openid_n=redisService.getOpenId(openid);
		regInfo.setOpenid(openid_n);
		RegInfo regInfoRet = regService.genSmsCode(regInfo,4);
		return RestJson.createSucces(regInfoRet);
	}
	
	
	@RequestMapping(value = "validateSmsCode.xcx"  )
	public RestJson validateSmsCode(String telphone, String smscode){
		RegInfo regInfo=new RegInfo();
		regInfo.setTelphone(telphone);
		regInfo.setSmscode(smscode);
		boolean flag = regService.validateSmsCode(regInfo);
		return RestJson.createSucces(flag);
	}
	
	
	@RequestMapping(value = "regUser.xcx"  )
	public RestJson regUser(String telphone) {
		RegInfo regInfo = regService.getRegInfoByTel(telphone);
		if(regInfo.getAccountId()==null) {
			regInfo.setUserName(telphone);
			regInfo.setTelphone(telphone);
			regInfo = regService.regUser(regInfo);
			
		}
		return RestJson.createSucces(regInfo);
	}
	
	@RequestMapping(value = "getOpenId.xcx")
	public RestJson getOpenId(String code){
		
		String url="https://api.weixin.qq.com/sns/jscode2session?appid=wx9a605545c03d6b9e&secret=5df1055596a0f04e6a36059b59311d28&js_code="+code+"&grant_type=authorization_code";
		WxLogin wxLogin = restTemplate.getForObject(url, WxLogin.class);
		
		String openid=wxLogin.getOpenid();
		String openidMd5 = redisService.putOpenId(openid);
		wxLogin.setOpenid(openidMd5);
	    System.out.println("wxLogin.getOpenid()="+wxLogin.getOpenid());
		return RestJson.createSucces(wxLogin);
	}
	
	

	
	
	
	
	
	
	

}
