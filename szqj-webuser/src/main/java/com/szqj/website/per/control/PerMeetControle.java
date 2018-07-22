package com.szqj.website.per.control;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.szqj.service.domain.Meet;
import com.szqj.service.domain.MeetRepository;
import com.szqj.service.domain.MeetSignUp;
import com.szqj.service.domain.MeetSignUpRepository;
import com.szqj.service.domain.MyPersonRepository;
import com.szqj.service.domain.Person;
import com.szqj.springmvc.Token;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.domain.Dict;
import com.szqj.weborg.domain.DictRepository;
import com.szqj.weborg.domain.FileInfo;
import com.szqj.weborg.domain.FileInfoRepository;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class PerMeetControle {
	
	@Autowired
	private MeetRepository meetRepository;
	
	@Autowired
	private FileInfoRepository fileInfoRepository;
	
	@Autowired
	private MeetSignUpRepository meetSignUpRepository;
	
	@Autowired
	private MyPersonRepository personRepository;
	
	
	/**
	 * 会议展览
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/per/meet.html"  )
	public String index_meet(Integer pageNum, Integer size, ModelMap modelMap){
		PageRequest pageable=Tools.getPage(pageNum, 5);
		Page<Meet> page=meetRepository.findPage(pageable);
		
		List<Meet> l = page.getContent();
		HashMap map=new HashMap();
		for(Meet c:l) {
	         个人报名状态	
		}
		
		modelMap.put("page", page);
		modelMap.put("fileMap", map);
		return "per/meet"; 
	}
	
	
	@RequestMapping(value = "/per/meet/detail.html"  )
	public String meet_detail(String meetId, ModelMap modelMap){
		Meet meet = meetRepository.findById(meetId).get();
		String tFiled = meet.getMeetPicId();
		List<FileInfo> files = fileInfoRepository.findByBussinessId(tFiled);
		if(files!=null&&files.size()>0) {
			modelMap.put("meetpath", files.get(0).getFileWebPath());
		}
		
		modelMap.put("meet", meet);
		return "per/meetdetail"; 
	}
	
	
	@Token(save = true)
	@RequestMapping(value = "/per/meet/signup.html"  )
	public String meet_signup(@SessionAttribute Account account,String meetId, ModelMap modelMap){
		Meet meet = meetRepository.findById(meetId).get();
		List<Person> persons = personRepository.findByAccountId(account.getAccountId());
		modelMap.put("meet", meet);
		modelMap.put("person", persons.get(0));
		return "per/meetsignup"; 
	}
	
	@Token(remove = true)
	@RequestMapping(value = "/per/meet/saveSignup.do"  )
	public String saveSignup(@SessionAttribute Account account, MeetSignUp meetSignUp, ModelMap modelMap){
		meetSignUp.setCreateDate(new Date());
		meetSignUp.setAccountId(account.getAccountId());
		meetSignUpRepository.save(meetSignUp);
		Meet meet = meetRepository.findById(meetSignUp.getMeetId()).get();
		modelMap.put("meet", meet);
		return "per/meetsignupsuccess"; 
	}
	
	
	
}
