package com.szqj.website.control;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.szqj.cms.domain.ContentInfo;
import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.Meet;
import com.szqj.service.domain.MeetPlan;
import com.szqj.service.domain.MeetPlanRepository;
import com.szqj.service.domain.MeetRepository;
import com.szqj.service.domain.Product;
import com.szqj.service.domain.ZbInfo;
import com.szqj.train.domain.TrainPlan;
import com.szqj.weborg.service.IndexService;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class IndexNewControle {
	
	
	@Autowired
	private IndexService indexService;
	
	@Autowired
	private MeetRepository meetRepository;
	
	@Autowired
	private MeetPlanRepository meetPlanRepository;
	

	
	/**
	 * 首页
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/indexnew.do"  )
	public String index(ModelMap modelMap){
		
		HashMap map=new HashMap();
		
		List<ContentInfo> sgNewList =indexService.getSGNews();
		List<ContentInfo> sgybNewList =indexService.getSGYBNews();
		modelMap.put("sgNewList", sgNewList);
		modelMap.put("sgybNewList", sgybNewList);
		
		
		List<ContentInfo> ywNewList =indexService.getYwNews(map);
		List<ContentInfo> qyNewList =indexService.getQyNews();
		List<ContentInfo> gjNewList = indexService.getGjNews();
		List<ContentInfo> yqNewList =indexService.getYqNews();
		
		
		modelMap.put("ywNewList", ywNewList);
		modelMap.put("qyNewList", qyNewList);
		modelMap.put("gjNewList", gjNewList);
		modelMap.put("yqNewList", yqNewList);
		
		List<ContentInfo> spNewList = indexService.getSpNews(map);
		modelMap.put("spNewList", spNewList);
		
		Integer zcNum = indexService.getNumByZc();
		Integer flNum = indexService.getNumByFl();
		Integer bjNum = indexService.getNumByBj();
		Integer lwNum = indexService.getNumByLw();
		
		modelMap.put("zcNum", zcNum);
		modelMap.put("flNum", flNum);
		modelMap.put("bjNum", bjNum);
		modelMap.put("lwNum", lwNum);
		
	
		modelMap.put("fileMap", map);
		
		setMainMeet(modelMap);
		
		return "index_temp_new";
	}
	
	
	/**
     * 设置主要会议
     * @param modelMap
     */
	private void setMainMeet(ModelMap modelMap) {
		List<Meet> l = meetRepository.findMain();
		if(l!=null&&l.size()!=0) {
			List<MeetPlan> meetlist = meetPlanRepository.findByMeetId(l.get(0).getMeetId());
			modelMap.put("mainMeet", l.get(0));
			modelMap.put("meetlist", meetlist);
		}
		
	}
	
	

	
	
	
	
	
	

}
