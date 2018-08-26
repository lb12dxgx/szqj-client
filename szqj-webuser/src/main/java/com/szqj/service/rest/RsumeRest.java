package com.szqj.service.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.service.domain.Person;
import com.szqj.service.domain.MyPersonRepository;
import com.szqj.service.domain.Meet;
import com.szqj.service.domain.MeetRepository;
import com.szqj.service.domain.MyPersonRepository;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;

@RestController
@RequestMapping("/system/resume/")
@EnableAutoConfiguration
public class RsumeRest {
	
	@Autowired
	private MyPersonRepository personRepository;
	
	

	@RequestMapping(value = "list.do"  )
	public RestJson list( String jobName,String personName,Integer pageNum, Integer size){
		
		Page<Person> page=null;
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		if(StringUtils.isNotBlank(personName)) {
			page =  personRepository.findAdminPageByPersonName(personName, pageable);
		}else if(StringUtils.isNotBlank(jobName)) {
			page = personRepository.findAdminPageByPersonName(jobName, pageable);
		}else {
			page =personRepository.findPage(pageable);
		}
		
		return RestJson.createSucces(page);
	}
	
	
	@RequestMapping(value = "get.do"  )
	public RestJson get(String personId){
		Person person = personRepository.findById(personId).get();
		return RestJson.createSucces(person);
	}
	
	
	@RequestMapping(value = "changeLevel.do"  )
	public RestJson changeLevel(String personId){
		Person person = personRepository.findById(personId).get();
		if(person.getLevel()==10) {
			person.setLevel(20);
		}else {
			person.setLevel(10);
		}
	
		personRepository.save(person);
		return RestJson.createSucces();
	}
	
	
	@RequestMapping(value = "delete.do"  )
	public RestJson delete(String personId, Integer size){
		personRepository.deleteById(personId);
		return RestJson.createSucces();
	}
	

}
