package com.szqj.xcx.rest;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.szqj.service.domain.AccidentInfo;
import com.szqj.service.domain.AccidentInfoRepository;
import com.szqj.service.domain.MyPersonRepository;
import com.szqj.service.domain.Person;
import com.szqj.util.ConstantUtils;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.domain.AccountRepository;
import com.szqj.weborg.domain.FileInfo;
import com.szqj.weborg.domain.FileInfoRepository;

@RestController
@RequestMapping("/xcx/login/")
@EnableAutoConfiguration
public class XcxAccidentInfoRest {
	
	@Autowired
	private AccidentInfoRepository accidentInfoRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private MyPersonRepository personRepository;
	
	
	
	/**
	 * 
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/accidentinfo/list.xcx"  )
	public RestJson accidentinfo(@ModelAttribute("openid") String openid,Integer pageNum, Integer size, String buyInfoName){
		List<Account> accounts = accountRepository.findByOpenid(openid);
		Account account = accounts.get(0);
		PageRequest pageable=Tools.getPage(pageNum, 6);
		Page<AccidentInfo> page=null;
		page=accidentInfoRepository.findPageByAccountId(account.getAccountId(), pageable);
		return RestJson.createSucces(page);
	}
	
	
	
	@RequestMapping(value = "/accidentinfo/add.xcx"  )
	public RestJson accidentinfoAdd( Map<String,String> map){
		map.put("accidentPicId", UUID.randomUUID().toString()+"p");
		map.put("accidentVideoId", UUID.randomUUID().toString()+"v");
		return RestJson.createSucces(map);
	}
	
	
	@RequestMapping(value = "/accidentinfo/save.xcx"  )
	public RestJson accidentinfoSave(@ModelAttribute("openid") String openid,AccidentInfo  accidentinfo){
		List<Account> accounts = accountRepository.findByOpenid(openid);
		Account account = accounts.get(0);
		List<Person> persons = personRepository.findByAccountId(account.getAccountId());
		accidentinfo.setPersonName(persons.get(0).getPersonName());
		accidentinfo.setTelePhone(persons.get(0).getTelePhone());
		accidentinfo.setAccountId(account.getAccountId());
		accidentinfo.setCreatDate(new Date());
		accidentInfoRepository.save(accidentinfo);
		return RestJson.createSucces(accidentinfo);
		
	}
	
	
	@RequestMapping(value = "/accidentinfo/get.xcx"  )
	public RestJson get(String accidentInfoId){
		AccidentInfo accidentInfo = accidentInfoRepository.findById(accidentInfoId).get();
		return RestJson.createSucces(accidentInfo);
		
	}

}
