package com.szqj.before.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szqj.before.domain.ApplyOrg;
import com.szqj.before.domain.ApplyOrgRepository;
import com.szqj.reg.domain.RegInfo;
import com.szqj.util.ConstantUtils;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.service.AccountService;

@Service
@Transactional
public class ApplyOrgService {

	@Autowired
	private ApplyOrgRepository applyOrgRepository;
	
	@Autowired
	private AccountService accountService;
	
	public void  saveApplyOrg(ApplyOrg applyOrg){
		Account account=saveAccount(applyOrg);
		applyOrg.setAccountId(account.getAccountId());
		applyOrgRepository.save(applyOrg);
	}
	
	
	private Account saveAccount(ApplyOrg applyOrg){
		Account account=new Account();
		account.setAccountName(applyOrg.getOrgName());
		account.setAccountPassword(applyOrg.getPassword());
		account.setState(ConstantUtils.ACCOUNT_STATE_START);
		account.setAccountType(ConstantUtils.ACCOUNT_COMPANYADMIN);
		accountService.save(account);
		return account;
	}
}
