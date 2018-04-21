package com.szqj.weborg.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szqj.util.ConstantUtils;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.domain.AccountRepository;

/**
 * 
 * @ClassName: AccountService
 * @Description: 账号登录类
 * @author zhanggy
 * 
 *
 */
@Service
@Transactional
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	

	public Account login(String accountName, String passsword) {
		Account account = new Account();
		List<Account> l = accountRepository.findByAccountName(accountName);
		if (l == null || l.size() == 0) {
			account.setLoginStr("用户名不存在"); 
		} else {
			if (StringUtils.isNotBlank(passsword)) {
				account = l.get(0);
				String md5Pass = Tools.MD5(passsword);
				if (!md5Pass.equals(account.getAccountPassword())) {
					account.setLoginStr("密码错误");
				}
			}else{
				account.setLoginStr("密码为空");
			}
		}

		account.setuPassword(passsword);

		return account;
	}
	
	
	public Page<Account> findPageByUserNameAndType(Integer accountType, String userName,Pageable pageable){
		if(StringUtils.isBlank(userName)){
			return accountRepository.findPageByAccountType(accountType, pageable);
		}
		return accountRepository.findPageByUserNameAndType(accountType, userName, pageable);
	}

	public boolean isExit(String accountName) {
		List<Account> l = accountRepository.findByAccountName(accountName);
		if (l == null || l.size() == 0) {
			return false;
		}

		return true;
	}

	public void save(Account account) {
		String pasw = account.getAccountPassword();
		String md5pasw = Tools.MD5(pasw);
		account.setAccountPassword(md5pasw);
		account.setState(ConstantUtils.ACCOUNT_STATE_START);
		accountRepository.save(account);
	}

	
	
	public Account getAccount(String accountId) {
		return accountRepository.findById(accountId).get();
	}

	

	public void update(Account account) {
		accountRepository.save(account);
	}

	public void delete(String accountId) {
		Account account=getAccount(accountId);
		account.setState(ConstantUtils.ACCOUNT_STATE_DEL);
		accountRepository.save(account);
	}
	
	public void stop(Account account) {
		account.setState(ConstantUtils.ACCOUNT_STATE_STOP);
		accountRepository.save(account);
	}

	public void reset(String accountId) {
		Account account=getAccount(accountId);
		account.setAccountPassword(ConstantUtils.ACCOUNT_DEFAULT_PASSWORD);
		save(account);
		
	}


	public void batchRemove(String ids) {
		String[] idAarray = StringUtils.split(ids,",");
		for(String id:idAarray){
			delete(id);
		}
		
	}
	
	public List<Account> findAllList(){
		return accountRepository.findAllList();
	}


	
	
	

}
