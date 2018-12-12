package com.szqj.weborg.rest;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.util.RestJson;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.service.AccountService;



@RestController
@RequestMapping("/token/")
@EnableAutoConfiguration
public class TokenRest {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "token.do")
	public RestJson token(String accountName,  String accountPassword,HttpServletRequest request){
		Account account = accountService.login(accountName, accountPassword);
		account.setToken(account.getAccountId());
		if(StringUtils.isBlank(account.getLoginStr())){
			request.getSession().setAttribute("account", account);
			return RestJson.createSucces(account);
		}else{
			return RestJson.createError(account);
		}
		
	}
	

}
