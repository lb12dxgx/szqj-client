package com.szqj.weborg.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping(value = "token.do"  )
	public RestJson token(String accountName,  String accountPassword){
		Account account = accountService.login(accountName, accountPassword);
		account.setToken(account.getAccountId());
		if(StringUtils.isBlank(account.getLoginStr())){
			return RestJson.createSucces(account);
		}else{
			return RestJson.createError(account);
		}
		
	}

}
