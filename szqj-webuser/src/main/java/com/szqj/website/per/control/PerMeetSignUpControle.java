package com.szqj.website.per.control;

import java.util.Date;
import java.util.List;

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
import com.szqj.weborg.domain.FileInfo;
import com.szqj.weborg.domain.FileInfoRepository;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class PerMeetSignUpControle {
	
	@Autowired
	private MeetRepository meetRepository;
	
	@Autowired
	private FileInfoRepository fileInfoRepository;
	
	@Autowired
	private MeetSignUpRepository meetSignUpRepository;
	
	@Autowired
	private MyPersonRepository personRepository;
	
	
	/**
	 * 会议信息
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/per/meet.html"  )
	public String index_meet(@SessionAttribute Account account,Integer pageNum, Integer size, ModelMap modelMap){
		PageRequest pageable=Tools.getPage(pageNum, 5);
		Page<Meet> page=meetRepository.findPage(pageable);
		
		List<Meet> l = page.getContent();
		
		for(Meet meet:l) {
			List<MeetSignUp> list = meetSignUpRepository.findListByMeetIdAndAccountId(meet.getMeetId(), account.getAccountId());
			if(list.size()>0){
				meet.setSignState(1);//已报名
			}else{
				if(meet.getStartDate().getTime()<new Date().getTime()){
					meet.setSignState(2);//报名结束
				}else{
					meet.setSignState(0);//报名
				}
			}
		}
		
		modelMap.put("page", page);
		
		return "per/meet"; 
	}
	
	
	@RequestMapping(value = "/per/meet/detail.html"  )
	public String meet_detail(@SessionAttribute Account account,String meetId, ModelMap modelMap){
		Meet meet = meetRepository.findById(meetId).get();
		
		List<MeetSignUp> list = meetSignUpRepository.findListByMeetIdAndAccountId(meet.getMeetId(), account.getAccountId());
		if(list.size()>0){
			meet.setSignState(1);//已报名
		}else{
			if(meet.getStartDate().getTime()<new Date().getTime()){
				meet.setSignState(2);//报名结束
			}else{
				meet.setSignState(0);//报名
			}
		}
		
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
