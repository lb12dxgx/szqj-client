package com.szqj.weborg.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.util.RestJson;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.service.AccountService;



@RestController
@RequestMapping("/system/account/")
@EnableAutoConfiguration
public class AccountRest {
	
	@Autowired
	private AccountService accountService;
	
	
	//根据账号类型返回账号列表
	@RequestMapping(value = "list.do"  )
	
	public RestJson list( Integer accountType,   String userName,   Integer pageNum, Integer size){
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		Page<Account> page = accountService.findPageByUserNameAndType(accountType, userName, pageable);
		return RestJson.createSucces(page);
	}
	
	
	//保存账号信息
	@RequestMapping(value = "save.do"  )
	public RestJson save( Account account){
		account.setAccountType(10);
		accountService.save(account);
		return RestJson.createSucces(account);
	}
	
	//更新
	@RequestMapping(value = "update.do"  )
	public RestJson update( Account account){
			accountService.update(account);
			return RestJson.createSucces(account);
	}
	
	//删除
	@RequestMapping(value = "delete.do"  )
	public RestJson delete( String accountId){
		accountService.delete(accountId);
		return RestJson.createSucces();
	}
	
	@RequestMapping(value = "reset.do"  )
	public RestJson reset(String accountId) {
		accountService.reset(accountId);
		return RestJson.createSucces();
	}
	
	@RequestMapping(value = "alllist.do"  )
	public RestJson accountAllList() {
		List<Account> l = accountService.findAllList();
		return RestJson.createSucces(l);
	}
		
	
	
	

}
