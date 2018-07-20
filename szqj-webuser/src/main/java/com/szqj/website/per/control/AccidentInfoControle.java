package com.szqj.website.per.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.szqj.service.domain.AccidentInfo;
import com.szqj.service.domain.AccidentInfoRepository;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;


@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class AccidentInfoControle {
	
	
	@Autowired
	private AccidentInfoRepository accidentInfoRepository;
	
	/**
	 * 供求信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/per/accidentinfo.html"  )
	public String accidentinfo(@SessionAttribute Account account,Integer pageNum, Integer size, String buyInfoName, ModelMap modelMap){
		PageRequest pageable=Tools.getPage(pageNum, 6);
		Page<AccidentInfo> page=null;
		page=accidentInfoRepository.findPageByAccountId(account.getAccountId(), pageable);
		modelMap.put("page", page);
		return "per/accidentinfo"; 
	}
	
	
	
	@RequestMapping(value = "/per/accidentinfo/add.html"  )
	public String accidentinfoAdd(){
		return "per/accidentinfo_add"; 
	}
	
	
	@RequestMapping(value = "/per/accidentinfo/save.do"  )
	public String accidentinfoSave(AccidentInfo  accidentinfo,  ModelMap modelMap){
		accidentInfoRepository.save(accidentinfo);
		return "redirect:/per/accidentinfo.html"; 
		
	}
	
	@RequestMapping(value = "/per/accidentinfo/edit.html"  )
	public String accidentinfoEdit(String accidentInfod,  ModelMap modelMap){
		AccidentInfo  accidentinfo = accidentInfoRepository.findById(accidentInfod).get();
		modelMap.put("accidentinfo", accidentinfo);
		return "per/accidentinfo_edit"; 
	}
	
	
	@RequestMapping(value = "/per/accidentinfo/update.do"  )
	public String accidentinfoUpdate(AccidentInfo  accidentinfo,  ModelMap modelMap){
		AccidentInfo  accidentinfoRet = accidentInfoRepository.findById(accidentinfo.getAccidentInfod()).get();
		Tools.copyBeanForUpdate(accidentinfo, accidentinfoRet);
		accidentInfoRepository.save(accidentinfoRet);
		return "redirect:/per/accidentinfo.html"; 
		
	}
	
	@RequestMapping(value = "/per/accidentinfo/del.do"  )
	public String accidentinfoDel(String  accidentInfod,  ModelMap modelMap){
		accidentInfoRepository.deleteById(accidentInfod);
		return "redirect:/per/accidentinfo.html"; 
		
	}


}
