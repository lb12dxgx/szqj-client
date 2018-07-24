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
import com.szqj.train.domain.TrainPlan;
import com.szqj.train.domain.TrainPlanRepository;
import com.szqj.train.domain.TrainSignUp;
import com.szqj.train.domain.TrainSignUpRepository;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.domain.FileInfo;
import com.szqj.weborg.domain.FileInfoRepository;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class PerTrainSignUpControle {
	
	@Autowired
	private TrainPlanRepository trainPlanRepository;
	

	
	@Autowired
	private TrainSignUpRepository trainSignUpRepository;
	
	@Autowired
	private MyPersonRepository personRepository;
	
	
	/**
	 * 会议信息
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/per/train.html"  )
	public String index_train(@SessionAttribute Account account,Integer pageNum, Integer size, ModelMap modelMap){
		PageRequest pageable=Tools.getPage(pageNum, 5);
		Page<TrainPlan> page=trainPlanRepository.findPage(pageable);
		
		List<TrainPlan> l = page.getContent();
		
		for(TrainPlan trainPlan:l) {
			List<TrainSignUp> list = trainSignUpRepository.findListByTrainPlanIdAndAccountId(trainPlan.getTrainPlanId(), account.getAccountId());
			if(list.size()>0){
				trainPlan.setSignState(1);//已报名
			}else{
				if(trainPlan.getTrainStartDate().getTime()<new Date().getTime()){
					trainPlan.setSignState(2);//报名结束
				}else{
					trainPlan.setSignState(0);//报名
				}
			}
		}
		
		modelMap.put("page", page);
		
		return "per/train"; 
	}
	
	
	@RequestMapping(value = "/per/train/detail.html"  )
	public String train_detail(@SessionAttribute Account account,String trainPlanId, ModelMap modelMap){
		TrainPlan trainPlan = trainPlanRepository.findById(trainPlanId).get();
		
		List<TrainSignUp> list = trainSignUpRepository.findListByTrainPlanIdAndAccountId(trainPlan.getTrainPlanId(), account.getAccountId());
		if(list.size()>0){
			trainPlan.setSignState(1);//已报名
		}else{
			if(trainPlan.getTrainStartDate().getTime()<new Date().getTime()){
				trainPlan.setSignState(2);//报名结束
			}else{
				trainPlan.setSignState(0);//报名
			}
		}
	
		modelMap.put("trainPlan", trainPlan);
		return "per/traindetail"; 
	}
	
	
	@Token(save = true)
	@RequestMapping(value = "/per/train/signup.html"  )
	public String train_signup(@SessionAttribute Account account,String trainPlanId, ModelMap modelMap){
		TrainPlan trainPlan = trainPlanRepository.findById(trainPlanId).get();
		List<Person> persons = personRepository.findByAccountId(account.getAccountId());
		modelMap.put("trainPlan", trainPlan);
		modelMap.put("person", persons.get(0));
		return "per/trainsignup"; 
	}
	
	@Token(remove = true)
	@RequestMapping(value = "/per/train/saveSignup.do"  )
	public String saveSignup(@SessionAttribute Account account, TrainSignUp trainSignUp, ModelMap modelMap){
		trainSignUp.setCreateDate(new Date());
		trainSignUp.setAccountId(account.getAccountId());
		trainSignUpRepository.save(trainSignUp);
		TrainPlan trainPlan = trainPlanRepository.findById(trainSignUp.getTrainPlanId()).get();
		modelMap.put("trainPlan", trainPlan);
		return "per/trainsignupsuccess"; 
	}
	
	
	
}
