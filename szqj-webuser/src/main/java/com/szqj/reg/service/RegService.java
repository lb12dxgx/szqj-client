package com.szqj.reg.service;

import java.util.Date;
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
	private EnterpriseRepository enterpriseRepository;
	
	@Autowired 
	private MyPersonRepository personRepository;
	

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
		/*try {
			SmsTools.alSendSms(smscode,regInfo.getTelphone());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return regInfoRet;
	}
	
	
	public boolean isExitByTelphone(RegInfo regInfo){
		
		if(1==regInfo.getType()){
			List<Enterprise> list = enterpriseRepository.findByTelePhone(regInfo.getTelphone());
			if(list==null||list.size()==0){
				return true;
			}else{
				 return false;
			}
		}else{
			List<Person> list = personRepository.findByTelePhone(regInfo.getTelphone());
			if(list==null||list.size()==0){
				return true;
			}else{
				 return false;
			}
		}
	
	}
	
	
	public boolean isExitByUserCode(String userCode) {
		List<Person> list = personRepository.findByUserCode(userCode);
		if(list==null||list.size()==0){
			return true;
		}else{
			 return false;
		}
	}
	
	public Account getAccountByOpenid(String openid) {
		List<Account> list = accountService.findByOpenid(openid);
		if(list==null||list.size()==0){
			return null;
		}
		return list.get(0);
	}
	
	public Person getPersonByOpenid(String openid) {
		List<Person> list = personRepository.findByOpenid(openid);
		if(list==null||list.size()==0){
			return null;
		}
		return list.get(0);
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
	 * 根据手机号返回注册信息
	 * @param telphone
	 * @return
	 */
	public RegInfo getRegInfoByTel(String telphone) {
		RegInfo regInfo=regInfoRepository.findByTelphone(telphone);
		return regInfo;
	}
	
	
	/**
	 * 创建网站注册个人与企业账户信息
	 * @param regInfo
	 * @return
	 */
	public RegInfo regUser(RegInfo regInfo) {
		RegInfo regInfoRet=null;
		if(regInfo.getReginfoId()!=null) {
			regInfoRet=regInfoRepository.findById(regInfo.getReginfoId()).get();
		}else {
			regInfoRet=regInfoRepository.findByTelphone(regInfo.getTelphone());
		}
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
			enterprise.setOpenid(regInfo.getOpenid());
			enterpriseRepository.save(enterprise);
		}
		
		if(regInfoRet.getType()==ConstantUtils.REG_PERSON){
			Person person=new Person();
			person.setAccountId(account.getAccountId());
			person.setUserCode(regInfoRet.getUserCode());
			person.setTelePhone(regInfoRet.getTelphone());
			person.setPersonName(regInfoRet.getRealName());
			person.setCreateDate(new Date());
			person.setOpenid(regInfo.getOpenid());
			personRepository.save(person);
		}
		
		return regInfoRet;
		
	}
	
	
	


	
	private Account saveAccount(RegInfo regInfo){
		Account account=new Account();
		account.setAccountName(regInfo.getUserName());
		account.setAccountPassword(regInfo.getPassword());
		account.setState(ConstantUtils.ACCOUNT_STATE_START);
		account.setOpenid(regInfo.getOpenid());
		if(regInfo.getType()==ConstantUtils.REG_PERSON){
			account.setAccountType(ConstantUtils.ACCOUNT_PERSON);
		}else{
			account.setAccountType(ConstantUtils.ACCOUNT_COMPANY);
		}
		accountService.save(account);
		
		return account;
	}
	
	
	public String getRandomCode(int count) {
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
