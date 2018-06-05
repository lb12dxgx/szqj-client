package com.szqj.reg.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szqj.company.domain.Company;
import com.szqj.company.domain.CompanyRepository;
import com.szqj.reg.domain.RegInfo;
import com.szqj.reg.domain.RegInfoRepository;
import com.szqj.util.ConstantUtils;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.service.AccountService;
import com.szqj.yun.SmsTools;

@Service
@Transactional
public class RegService {
	
	@Autowired 
	private RegInfoRepository regInfoRepository;
	
	@Autowired 
	private AccountService accountService;
	
	@Autowired 
	private CompanyRepository companyRepository;
	
	public RegInfo genSmsCode(RegInfo regInfo,int count ){
		RegInfo regInfoRet=regInfoRepository.findByTelphone(regInfo.getTelphone());
		if(regInfoRet==null){
			regInfoRet=new RegInfo();
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


	
	
	
	public boolean validateSmsCode(RegInfo regInfo) {
		RegInfo regInfoRet=regInfoRepository.findByTelphone(regInfo.getTelphone());
		if(regInfoRet.getSmscode().equals(regInfo.getSmscode())){
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
	 * 创建挖掘申请单位信息
	 * @param regInfo
	 * @return
	 */
	public RegInfo regBeforeComapnySubmit(String reginfoId, Company company) {
		RegInfo regInfo = regInfoRepository.findById(reginfoId).get();
		company.setAccountId(regInfo.getAccountId());
		companyRepository.save(company);
		return regInfo;
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
