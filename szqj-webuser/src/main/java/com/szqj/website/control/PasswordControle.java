package com.szqj.website.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.szqj.reg.domain.RegInfo;
import com.szqj.reg.domain.RegInfoRepository;
import com.szqj.reg.service.RegService;
import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.EnterpriseRepository;
import com.szqj.service.domain.MyPersonRepository;
import com.szqj.service.domain.Person;
import com.szqj.springmvc.Token;
import com.szqj.util.ConstantUtils;
import com.szqj.util.RestJson;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.service.AccountService;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class PasswordControle {
	
	
	
	@Autowired
	private RegService regService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired 
	private EnterpriseRepository enterpriseRepository;
	
	@Autowired 
	private MyPersonRepository personRepository;
	

	
	
	/**
	 * 找回密码
	 * @param modelMap
	 * @return
	 */
	@Token(save = true)
	@RequestMapping(value = "password.html"  )
	public String password(ModelMap modelMap,String backurl,HttpServletRequest request){
		
		return "login/password.html"; 
	}
	
	/**
	 * 找回密码
	 * @param modelMap
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "getPassWordSmsCode.do"  )
	public RestJson getPassWordSmsCode(RegInfo regInfo){
		Account account = accountService.getByAccountName(regInfo.getUserName());
		
		if(account==null) {
			return RestJson.createError("账号不正确");
		};
		if(regInfo.getType()==0) {
			 List<Person> l = personRepository.findByAccountId(account.getAccountId());
			 if(l==null||l.size()==0) {
				 return RestJson.createError("用户类型不对");
			 }
			 Person person =l.get(0);
			 regInfo.setTelphone(person.getTelePhone());
		}else {
			
			List<Enterprise> list = enterpriseRepository.findByAccountId(account.getAccountId());
		
			 if(list==null||list.size()==0) {
				 return RestJson.createError("用户类型不对");
			 }
				Enterprise enterprise = list.get(0);
			regInfo.setTelphone(enterprise.getTelphone());
		}
		RegInfo regInfoRet = regService.genSmsCode(regInfo,4);
		return RestJson.createSucces(regInfoRet.getSmscode());
	}
	
	
	@ResponseBody
	@RequestMapping(value = "validatePassWordSmsCode.do"  )
	public RestJson validatePassWordSmsCode(RegInfo regInfo){
		Account account = accountService.getByAccountName(regInfo.getUserName());
		if(regInfo.getType()==0) {
			 List<Person> l = personRepository.findByAccountId(account.getAccountId());
			 if(l==null||l.size()==0) {
				 return RestJson.createError("用户类型不对");
			 }
			 Person person =l.get(0);
			 regInfo.setTelphone(person.getTelePhone());
		}else {
			
			List<Enterprise> list = enterpriseRepository.findByAccountId(account.getAccountId());
		
			 if(list==null||list.size()==0) {
				 return RestJson.createError("用户类型不对");
			 }
				Enterprise enterprise = list.get(0);
			regInfo.setTelphone(enterprise.getTelphone());
		}
		boolean flag = regService.validateSmsCode(regInfo);
		return RestJson.createSucces(!flag);
	}
	
	
	@Token(remove =true,save = true)
	@RequestMapping(value = "submitPassword.do"  )
	public String submitPassword(RegInfo regInfo,ModelMap modelMap){
		Account account = accountService.getByAccountName(regInfo.getUserName());
		modelMap.put("accountId", account.getAccountId());
		return "login/passwordchang"; 
	}
	
	
	@Token(remove =true,save = true)
	@RequestMapping(value = "updatePassword.do"  )
	public String updatePassword(String accountId,String password, ModelMap modelMap){
		Account account = accountService.getAccount(accountId);
		account.setAccountPassword(password);
		accountService.save(account);
		return "login/passwordsuccess"; 
	}
	
	
	

}
