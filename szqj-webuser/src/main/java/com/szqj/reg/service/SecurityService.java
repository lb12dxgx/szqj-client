package com.szqj.reg.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szqj.reg.domain.RegInfo;
import com.szqj.reg.domain.RegInfoRepository;
import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.EnterpriseRepository;
import com.szqj.service.domain.MyPersonRepository;
import com.szqj.service.domain.Person;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.service.AccountService;
import com.szqj.yun.SmsTools;

@Service
@Transactional
public class SecurityService {
	
	
	@Autowired 
	private RegInfoRepository regInfoRepository;
	
	@Autowired 
	private AccountService accountService;
	
	@Autowired 
	private EnterpriseRepository enterpriseRepository;
	
	@Autowired 
	private MyPersonRepository personRepository;
	

	/**
	 * 验证原先密码
	 * @param accountId
	 * @param password
	 * @return
	 */
	public boolean repassword(String accountId, String password) {
		Account account = accountService.getAccount(accountId);
		
		if(account.getAccountPassword().equals(Tools.MD5(password))){
			return true;
		}
		return false;
	}
	
	/**
	 * 保存密码
	 * @param accountId
	 * @param password
	 * @param oldpassword 
	 */
	public void savePassWord(String accountId, String password, String oldpassword) {
		Account account = accountService.getAccount(accountId);
		account.setAccountPassword(password);
		accountService.save(account);
		
	}

	
    /**
     * 验证手机号码是否存在
     * @param accountId
     * @param telphone
     * @return
     */
	public boolean isExitByPerTelphone(String accountId, String telphone) {
		List<Person> l = personRepository.findByAccountIdAndTelePhone(accountId,telphone);
		if(l==null||l.size()==0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 发换手机的短信
	 * @param telphone
	 * @return
	 */
	public String sendSmsCode(String telphone) {
		String smscode = getRandomCode(4);
		try {
			SmsTools.alSendSmsByEdit(smscode,telphone);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return smscode;
	}

	/**
	 * 保存新的个人手机号码
	 * @param accountId
	 * @param telphone
	 */
	public void savePerTelphone(String accountId, String telphone) {
		List<Person> list = personRepository.findByAccountId(accountId);
		Person person=list.get(0);
		person.setTelePhone(telphone);
		personRepository.save(person);
		
	}
	
	
   /**
    * 判断企业电话是否存在
    * @param accountId
    * @param telphone
    * @return
    */
	public boolean isExitByEmpTelphone(String accountId, String telphone) {
		List<Enterprise> l = enterpriseRepository.findByAccountIdAndTelePhone(accountId,telphone);
		if(l==null||l.size()==0){
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * 保存新的企业注册手机号码
	 * @param accountId
	 * @param telphone
	 */
	public void saveEmpTelphone(String accountId, String telphone) {
		List<Enterprise> l = enterpriseRepository.findByAccountId(accountId);
		Enterprise enterprise=l.get(0);
		enterprise.setTelphone(telphone);
		enterpriseRepository.save(enterprise);
	}
	
	
	private String getRandomCode(int count) {
		String invitecode="";
		String str="0123456789";
	    Random r = new Random(System.currentTimeMillis());
	    for (int i = 0; i < count; i++) {
	        int d =r.nextInt(10);
	        invitecode=  invitecode+str.charAt(d);
	    }
	    return invitecode;
	}


}
