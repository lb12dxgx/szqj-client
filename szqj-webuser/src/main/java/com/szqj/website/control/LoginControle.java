package com.szqj.website.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.szqj.reg.domain.RegInfo;
import com.szqj.reg.service.RegService;
import com.szqj.util.RestJson;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class LoginControle {
	
	@Autowired
	private RegService regService;
	/**
	 * 网站登陆
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "login.html"  )
	public String login(ModelMap modelMap){
		return "login/login.html"; 
	}
	
	
	
	
	@RequestMapping(value = "token.do"  )
	public String token(String accountName, String accountPassword,ModelMap modelMap){
		return "login"; 
	}
	
	
	/**
	 * 网站登陆
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "reg.html"  )
	public String reg(ModelMap modelMap){
		return "login/reg"; 
	}
	
	@ResponseBody
	@RequestMapping(value = "getSmsCode.do"  )
	public RestJson getSmsCode(RegInfo regInfo){
		RegInfo regInfoRet = regService.genSmsCode(regInfo,4);
		return RestJson.createSucces(regInfoRet.getSmscode());
	}
	
	@ResponseBody
	@RequestMapping(value = "validateSmsCode.do"  )
	public RestJson validateSmsCode(RegInfo regInfo){
		boolean flag = regService.validateSmsCode(regInfo);
		return RestJson.createSucces(!flag);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "isExitByTelphone.do"  )
	public boolean isExitByTelphone(RegInfo regInfo){
		boolean flag = regService.isExitByTelphone(regInfo);
		return !flag;
	}
	
	@RequestMapping(value = "submitreg.html"  )
	public String submitonereg(ModelMap modelMap){
		return "login/regtwo"; 
	}
	
	@RequestMapping(value = "submitregsuccess.html"  )
	public String submittworeg(ModelMap modelMap){
		return "login/regsuccess"; 
	}
	
	
	
	

}
