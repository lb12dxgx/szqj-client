package com.szqj.reg.service;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szqj.reg.domain.RegInfo;
import com.szqj.reg.domain.RegInfoRepository;
import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.EnterpriseRepository;
import com.szqj.service.domain.Person;
import com.szqj.service.domain.PersonRepository;
import com.szqj.util.ConstantUtils;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.service.AccountService;

@Service
@Transactional
public class RegService {
	
	@Autowired 
	private RegInfoRepository regInfoRepository;
	
	@Autowired 
	private AccountService accountService;
	
	@Autowired 
	private EnterpriseRepository enterpriseRepository;
	
	@Autowired 
	private PersonRepository personRepository;
	

	public RegInfo genSmsCode(RegInfo regInfo,int count ){
		RegInfo regInfoRet=regInfoRepository.findByTelphone(regInfo.getTelphone());
		if(regInfoRet==null){
			regInfoRet=new RegInfo();
			regInfoRet.setCreateDate(new Date());
		}
		Tools.copyBeanForUpdate(regInfo, regInfoRet);
	    String smscode = getRandomCode(count);
	    regInfoRet.setSmscode(smscode);
		regInfoRepository.save(regInfoRet);
		try {
			//SmsTools.alSendSms(smscode,regInfo.getTelphone());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return regInfoRet;
	}
	
	
	public boolean isExitByTelphone(RegInfo regInfo){
		RegInfo regInfoRet=regInfoRepository.findByTelphone(regInfo.getTelphone());
		if(regInfoRet!=null&&regInfoRet.getAccountId()!=null){
			return true;
		}
		  return false;
	}
	
	
	public boolean isExitByUserCode(RegInfo regInfo){
		RegInfo regInfoRet=regInfoRepository.findByUserCode(regInfo.getUserCode());
		if(regInfoRet!=null&&regInfoRet.getAccountId()!=null){
			return true;
		}
		  return false;
	}
	
	
	public boolean isExitByUserName(RegInfo regInfo){
		RegInfo regInfoRet=regInfoRepository.findByUserName(regInfo.getUserName());
		if(regInfoRet!=null&&regInfoRet.getAccountId()!=null){
			return true;
		}
		  return false;
	}
	


	
	
	
	public boolean validateSmsCode(RegInfo regInfo) {
		RegInfo regInfoRet=regInfoRepository.findByTelphone(regInfo.getTelphone());
		if(regInfoRet!=null&&regInfoRet.getSmscode().equals(regInfo.getSmscode())){
			return true;
		}
		return false;
	}

	/**
	 * 创建挖掘申请用户信息
	 * @param regInfo
	 * @return
	 */
	
	public RegInfo regBeforeUser(RegInfo regInfo) {
		RegInfo regInfoRet=regInfoRepository.findByTelphone(regInfo.getTelphone());
		Tools.copyBeanForUpdate(regInfo, regInfoRet);
		regInfoRet.setType(ConstantUtils.REG_COMPANY);
		Account account=saveAccount(regInfoRet);
		regInfoRet.setAccountId(account.getAccountId());
		regInfoRet.setPassword(account.getAccountPassword());
		regInfoRepository.save(regInfoRet);
		return regInfoRet;
		
	}
	
	
	/**
	 * 创建网站注册个人与企业账户信息
	 * @param regInfo
	 * @return
	 */
	public RegInfo regUser(RegInfo regInfo) {
		RegInfo regInfoRet=regInfoRepository.findById(regInfo.getReginfoId()).get();
		Tools.copyBeanForUpdate(regInfo, regInfoRet);
		Account account=saveAccount(regInfoRet);
		regInfoRet.setAccountId(account.getAccountId());
		regInfoRet.setPassword(account.getAccountPassword());
		regInfoRepository.save(regInfoRet);
		
		if(regInfoRet.getType()==ConstantUtils.REG_COMPANY){
			Enterprise enterprise=new Enterprise();
			enterprise.setAccountId(account.getAccountId());
			enterprise.setEnterpriseName(regInfoRet.getUserName());
			enterprise.setEnterpriseCode(regInfoRet.getUserCode());
			enterprise.setTelphone(regInfoRet.getTelphone());
			enterprise.setCreateDate(new Date());
			enterprise.setLevel(10);
			enterpriseRepository.save(enterprise);
		}
		
		if(regInfoRet.getType()==ConstantUtils.REG_PERSON){
			Person person=new Person();
			person.setAccountId(account.getAccountId());
			person.setUserCode(regInfo.getUserCode());
			person.setTelePhone(regInfo.getTelphone());
			personRepository.save(person);
		}
		
		return regInfoRet;
		
	}
	
	
	


	
	private Account saveAccount(RegInfo regInfo){
		Account account=new Account();
		account.setAccountName(regInfo.getUserName());
		account.setAccountPassword(regInfo.getPassword());
		account.setState(ConstantUtils.ACCOUNT_STATE_START);
		if(regInfo.getType()==ConstantUtils.REG_PERSON){
			account.setAccountType(ConstantUtils.ACCOUNT_PERSON);
		}else{
			account.setAccountType(ConstantUtils.ACCOUNT_COMPANY);
		}
		accountService.save(account);
		
		return account;
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
