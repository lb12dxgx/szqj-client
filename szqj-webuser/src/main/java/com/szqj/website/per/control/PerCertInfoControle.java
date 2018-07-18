package com.szqj.website.per.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.MyPersonRepository;
import com.szqj.service.domain.PerCertInfoRepository;
import com.szqj.service.domain.PerCertInfo;
import com.szqj.service.domain.PerCertInfoRepository;
import com.szqj.service.domain.Person;
import com.szqj.weborg.domain.Account;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class PerCertInfoControle {
	
	@Autowired
	private MyPersonRepository personRepository;
	@Autowired
	private PerCertInfoRepository perCertInfoRepository;
	
	@RequestMapping(value = "/per/certinfo/add.html"  )
	public String certinfoAdd(@SessionAttribute Account account, ModelMap modelMap){
		List<Person> persons = personRepository.findByAccountId(account.getAccountId());
		modelMap.put("person", persons.get(0));
		return "per/certinfo_add"; 
	}
	
	@RequestMapping(value = "/per/certinfo/edit.html"  )
	public String certinfoEdit(String perCertInfoId, ModelMap modelMap){
		PerCertInfo perCertInfo = perCertInfoRepository.findById(perCertInfoId).get();
		modelMap.put("perCertInfo", perCertInfo);
		return "per/certinfo_edit"; 
	}
	
	@RequestMapping(value = "/per/certinfo/list.html"  )
	public String certinfoList(@SessionAttribute Account account, ModelMap modelMap){
		List<Person> persons = personRepository.findByAccountId(account.getAccountId());
		List<PerCertInfo> list = perCertInfoRepository.findList(persons.get(0).getPersonId());
		modelMap.put("list", list);
		return "per/certinfo_list"; 
	}
	
	
	@RequestMapping(value = "/per/certinfo/save.do"  )
	public String certinfoSave( PerCertInfo perCertInfo, ModelMap modelMap){
		perCertInfoRepository.save(perCertInfo);
		return "redirect:/per/certinfo/list.html"; 
	}
	
	
	@RequestMapping(value = "/per/certinfo/update.do"  )
	public String certinfoUpdate(PerCertInfo perCertInfo, ModelMap modelMap){
		perCertInfoRepository.save(perCertInfo);
		return "redirect:/per/certinfo/list.html"; 
	}
	
	
	@RequestMapping(value = "/per/certinfo/del.do"  )
	public String certinfoDel(String perCertInfoId, ModelMap modelMap){
		perCertInfoRepository.deleteById(perCertInfoId);
		return "redirect:/per/certinfo/list.html"; 
	}
	
		 

}
