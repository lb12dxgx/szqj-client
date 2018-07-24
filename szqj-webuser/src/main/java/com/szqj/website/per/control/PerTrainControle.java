package com.szqj.website.per.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.szqj.service.domain.MyPersonRepository;
import com.szqj.service.domain.PerTrainInfo;
import com.szqj.service.domain.PerTrainInfoRepository;
import com.szqj.service.domain.Person;
import com.szqj.weborg.domain.Account;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class PerTrainControle {
	
	@Autowired
	private MyPersonRepository personRepository;
	@Autowired
	private PerTrainInfoRepository perTrainInfoRepository;
	
	@RequestMapping(value = "/per/traininfo/add.html"  )
	public String traininfoAdd(@SessionAttribute Account account, ModelMap modelMap){
		List<Person> persons = personRepository.findByAccountId(account.getAccountId());
		modelMap.put("person", persons.get(0));
		return "per/traininfo_add"; 
	}
	
	@RequestMapping(value = "/per/traininfo/edit.html"  )
	public String traininfoEdit(String perTrainInfoId, ModelMap modelMap){
		PerTrainInfo perTrainInfo = perTrainInfoRepository.findById(perTrainInfoId).get();
		modelMap.put("perTrainInfo", perTrainInfo);
		return "per/traininfo_edit"; 
	}
	
	@RequestMapping(value = "/per/traininfo/list.html"  )
	public String traininfoList(@SessionAttribute Account account, ModelMap modelMap){
		List<Person> persons = personRepository.findByAccountId(account.getAccountId());
		List<PerTrainInfo> list = perTrainInfoRepository.findList(persons.get(0).getPersonId());
		modelMap.put("list", list);
		return "per/traininfo_list"; 
	}
	
	
	@RequestMapping(value = "/per/traininfo/save.do"  )
	public String traininfoSave( PerTrainInfo perTrainInfo, ModelMap modelMap){
		perTrainInfoRepository.save(perTrainInfo);
		return "redirect:/per/traininfo/list.html"; 
	}
	
	
	@RequestMapping(value = "/per/traininfo/update.do"  )
	public String traininfoUpdate(PerTrainInfo perTrainInfo, ModelMap modelMap){
		perTrainInfoRepository.save(perTrainInfo);
		return "redirect:/per/traininfo/list.html"; 
	}
	
	
	@RequestMapping(value = "/per/traininfo/del.do"  )
	public String traininfoDel(String perTrainInfoId, ModelMap modelMap){
		perTrainInfoRepository.deleteById(perTrainInfoId);
		return "redirect:/per/traininfo/list.html"; 
	}
	
		 


}
