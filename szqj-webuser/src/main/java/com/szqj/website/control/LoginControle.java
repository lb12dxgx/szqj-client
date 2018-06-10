package com.szqj.website.control;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.szqj.before.domain.ApplyOrg;
import com.szqj.company.domain.Company;
import com.szqj.reg.domain.RegInfo;
import com.szqj.reg.domain.RegInfoRepository;
import com.szqj.reg.service.RegService;
import com.szqj.springmvc.Token;
import com.szqj.util.RestJson;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.service.AccountService;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class LoginControle {
	
	@Autowired 
	private RegInfoRepository regInfoRepository;
	
	@Autowired
	private RegService regService;
	
	@Autowired
	private AccountService accountService;
	
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
	public String token(String userName, String password,ModelMap modelMap){
		Account account = accountService.login(userName, password);
		account.setToken(account.getAccountId());
		if(StringUtils.isBlank(account.getLoginStr())){
			
			 return "emp/index"; 
		}else{
			 modelMap.put("loginStr", account.getLoginStr());
			
			 return "login/login"; 
		}
		
	}
	
	
	/**
	 * 网站登陆
	 * @param modelMap
	 * @return
	 */
	@Token(save = true)
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
	
	
	@ResponseBody
	@RequestMapping(value = "isExitByUserName.do"  )
	public boolean isExitByUserName(RegInfo regInfo){
		boolean flag = regService.isExitByUserName(regInfo);
		return !flag;
	}
	
	@ResponseBody
	@RequestMapping(value = "isExitByUserCode.do"  )
	public boolean isExitByUserCode(RegInfo regInfo){
		boolean flag = regService.isExitByUserCode(regInfo);
		return !flag;
	}
	
	
	
	@Token(remove =true,save = true)
	@RequestMapping(value = "submitreg.do"  )
	public String submitonereg(String telphone,String smscode,ModelMap modelMap){
		RegInfo regInfo=regInfoRepository.findByTelphone(telphone);
		modelMap.put("regInfo", regInfo);
		return "login/regtwo"; 
	}
	
	@Token(remove =true)
	@RequestMapping(value = "submitregsuccess.do"  )
	public String submittworeg(RegInfo regInfo,ModelMap modelMap){
		RegInfo regInfoRt=regService.regUser(regInfo);
		modelMap.put("regInfo", regInfoRt);
		return "login/regsuccess"; 
	}
	
	
	
	

}
