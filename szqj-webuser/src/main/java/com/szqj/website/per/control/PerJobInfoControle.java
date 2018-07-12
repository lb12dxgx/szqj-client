package com.szqj.website.per.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.szqj.service.domain.MyPersonRepository;
import com.szqj.service.domain.PerJobInfo;
import com.szqj.service.domain.PerJobInfoRepository;
import com.szqj.service.domain.Person;
import com.szqj.weborg.domain.Account;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class PerJobInfoControle {
	
	@Autowired
	private MyPersonRepository personRepository;
	@Autowired
	private PerJobInfoRepository perJobInfoRepository;
	
	@RequestMapping(value = "/per/jobdetail/add.html"  )
	public String jobinfoAdd(@SessionAttribute Account account, ModelMap modelMap){
		List<Person> persons = personRepository.findByAccountId(account.getAccountId());
		modelMap.put("person", persons.get(0));
		return "per/jobdetail_add"; 
	}
	
	@RequestMapping(value = "/per/jobdetail/edit.html"  )
	public String jobinfoEdit(String perJobInfoId, ModelMap modelMap){
		PerJobInfo perJobInfo = perJobInfoRepository.findById(perJobInfoId).get();
		modelMap.put("perJobInfo", perJobInfo);
		return "per/jobdetail_edit"; 
	}
	
	@RequestMapping(value = "/per/jobdetail/list.html"  )
	public String jobinfoList(@SessionAttribute Account account, ModelMap modelMap){
		List<Person> persons = personRepository.findByAccountId(account.getAccountId());
		List<PerJobInfo> list = perJobInfoRepository.findList(persons.get(0).getPersonId());
		modelMap.put("list", list);
		return "per/jobdetail_list"; 
	}
	
	
	@RequestMapping(value = "/per/jobdetail/save.do"  )
	public String jobinfoSave( PerJobInfo perJobInfo, ModelMap modelMap){
		perJobInfoRepository.save(perJobInfo);
		return "redirect:/per/jobdetail/list.html"; 
	}
	
	
	@RequestMapping(value = "/per/jobdetail/update.do"  )
	public String jobinfoUpdate( PerJobInfo perJobInfo, ModelMap modelMap){
		perJobInfoRepository.save(perJobInfo);
		return "redirect:/per/jobdetail/list.html"; 
	}
	
	
	@RequestMapping(value = "/per/jobdetail/del.do"  )
	public String jobinfoDel(String perJobInfoId, ModelMap modelMap){
		perJobInfoRepository.deleteById(perJobInfoId);
		return "redirect:/per/jobdetail/list.html"; 
	}

}
