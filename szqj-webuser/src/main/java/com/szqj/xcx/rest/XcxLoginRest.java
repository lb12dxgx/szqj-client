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

import com.szqj.reg.domain.RegInfo;
import com.szqj.reg.service.RegService;
import com.szqj.util.RestJson;

@RestController
@RequestMapping("/xcx/")
@EnableAutoConfiguration
public class XcxLoginRest {
	@Autowired
	private RegService regService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	
	@RequestMapping(value = "isExitByTelphone.xcx"  )
	public RestJson isExitByTelphone(RegInfo regInfo){
		boolean flag = regService.isExitByTelphone(regInfo);
		return RestJson.createSucces(flag);
	}
	
	
	@RequestMapping(value = "getSmsCode.xcx"  )
	public RestJson getSmsCode(RegInfo regInfo){
		RegInfo regInfoRet = regService.genSmsCode(regInfo,4);
		return RestJson.createSucces(regInfoRet.getSmscode());
	}
	
	
	@RequestMapping(value = "validateSmsCode.xcx"  )
	public RestJson validateSmsCode(RegInfo regInfo){
		boolean flag = regService.validateSmsCode(regInfo);
		return RestJson.createSucces(!flag);
	}
	
	
	@RequestMapping(value = "regUser.xcx"  )
	public RestJson regUser(RegInfo regInfo) {
		RegInfo retRegInfo = regService.regUser(regInfo);
		return RestJson.createSucces(retRegInfo);
	}
	
	@RequestMapping(value = "getOpenId.xcx")
	public RestJson getOpenId(String code){
		
		String url="https://api.weixin.qq.com/sns/jscode2session?appid=wx9a605545c03d6b9e&secret=5df1055596a0f04e6a36059b59311d28&js_code="+code+"&grant_type=authorization_code";
		WxLogin wxLogin = restTemplate.getForObject(url, WxLogin.class);
	  
	    System.out.println("wxLogin.getOpenid()="+wxLogin.getOpenid());
		return RestJson.createSucces(wxLogin);
	}
	
	
	
	
	
	

}
