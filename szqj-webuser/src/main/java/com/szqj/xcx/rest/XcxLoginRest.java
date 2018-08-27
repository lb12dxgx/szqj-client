package com.szqj.xcx.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.reg.domain.RegInfo;
import com.szqj.reg.service.RegService;
import com.szqj.util.RestJson;

@RestController
@RequestMapping("/xcx/")
@EnableAutoConfiguration
public class XcxLoginRest {
	@Autowired
	private RegService regService;
	
	
	@ResponseBody
	@RequestMapping(value = "isExitByTelphone.xcx"  )
	public RestJson isExitByTelphone(RegInfo regInfo){
		boolean flag = regService.isExitByTelphone(regInfo);
		return RestJson.createSucces(flag);
	}
	
	@ResponseBody
	@RequestMapping(value = "getSmsCode.xcx"  )
	public RestJson getSmsCode(RegInfo regInfo){
		RegInfo regInfoRet = regService.genSmsCode(regInfo,4);
		return RestJson.createSucces(regInfoRet.getSmscode());
	}
	
	@ResponseBody
	@RequestMapping(value = "validateSmsCode.xcx"  )
	public RestJson validateSmsCode(RegInfo regInfo){
		boolean flag = regService.validateSmsCode(regInfo);
		return RestJson.createSucces(!flag);
	}
	
	
	
	

}
