package com.szqj.website.per.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.szqj.service.domain.Person;
import com.szqj.service.domain.PersonRepository;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.domain.FileInfo;
import com.szqj.weborg.domain.FileInfoRepository;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class PerControle {
	
	
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private FileInfoRepository fileInfoRepository;
	
	
	
	/**
	 * 个人信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/per/info.html"  )
	public String info(ModelMap modelMap,HttpServletRequest request){
		Account account = (Account)request.getSession().getAttribute("account");
		List<Person> persons = personRepository.findByAccountId(account.getAccountId());
		List<FileInfo> files = fileInfoRepository.findByBussinessId(persons.get(0).getPersonPicId());
		if(files!=null&&files.size()>0) {
			modelMap.put("fileWebPath", files.get(0).getFileWebPath());
		}
		modelMap.put("person", persons.get(0));
		return "per/info"; 
	}
	
	
	/**
	 * 企业信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/per/save.do"  ) 
	public String save(Person person, ModelMap modelMap){
		Person personRet = personRepository.findById(person.getPersonId()).get();
		Tools.copyBeanForUpdate(person, personRet);
		personRepository.save(personRet);
		return "redirect:/per/info.html"; 
	}
	
	/**
	 * 个人信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/per/resume.html"  )
	public String resume(ModelMap modelMap,HttpServletRequest request){
		Account account = (Account)request.getSession().getAttribute("account");
		List<Person> persons = personRepository.findByAccountId(account.getAccountId());
		List<FileInfo> files = fileInfoRepository.findByBussinessId(persons.get(0).getPersonPicId());
		if(files!=null&&files.size()>0) {
			modelMap.put("fileWebPath", files.get(0).getFileWebPath());
		}
		modelMap.put("person", persons.get(0));
		return "per/resume"; 
	}
	
	
}
