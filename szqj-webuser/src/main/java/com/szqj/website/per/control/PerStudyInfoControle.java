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
import com.szqj.service.domain.PerJobInfo;
import com.szqj.service.domain.PerJobInfoRepository;
import com.szqj.service.domain.PerStudyInfo;
import com.szqj.service.domain.PerStudyInfoRepository;
import com.szqj.service.domain.Person;
import com.szqj.weborg.domain.Account;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class PerStudyInfoControle {
	
	@Autowired
	private MyPersonRepository personRepository;
	@Autowired
	private PerStudyInfoRepository perStudyInfoRepository;
	
	@RequestMapping(value = "/per/studyinfo/add.html"  )
	public String studyinfoAdd(@SessionAttribute Account account, ModelMap modelMap){
		List<Person> persons = personRepository.findByAccountId(account.getAccountId());
		modelMap.put("person", persons.get(0));
		return "per/studyinfo_add"; 
	}
	
	@RequestMapping(value = "/per/studyinfo/edit.html"  )
	public String studyinfoEdit(String perStudyInfoId, ModelMap modelMap){
		PerStudyInfo perStudyInfo = perStudyInfoRepository.findById(perStudyInfoId).get();
		modelMap.put("perStudyInfo", perStudyInfo);
		return "per/studyinfo_edit"; 
	}
	
	@RequestMapping(value = "/per/studyinfo/list.html"  )
	public String studyinfoList(@SessionAttribute Account account, ModelMap modelMap){
		List<Person> persons = personRepository.findByAccountId(account.getAccountId());
		List<PerStudyInfo> list = perStudyInfoRepository.findList(persons.get(0).getPersonId());
		modelMap.put("list", list);
		return "per/studyinfo_list"; 
	}
	
	
	@RequestMapping(value = "/per/studyinfo/save.do"  )
	public String studyinfoSave( PerStudyInfo perStudyInfo, ModelMap modelMap){
		perStudyInfoRepository.save(perStudyInfo);
		return "redirect:/per/studyinfo/list.html"; 
	}
	
	
	@RequestMapping(value = "/per/studyinfo/update.do"  )
	public String studyinfoUpdate(PerStudyInfo perStudyInfo, ModelMap modelMap){
		perStudyInfoRepository.save(perStudyInfo);
		return "redirect:/per/studyinfo/list.html"; 
	}
	
	
	@RequestMapping(value = "/per/studyinfo/del.do"  )
	public String studyinfoDel(String perStudyInfoId, ModelMap modelMap){
		perStudyInfoRepository.deleteById(perStudyInfoId);
		return "redirect:/per/studyinfo/list.html"; 
	}


}
