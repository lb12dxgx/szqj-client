package com.szqj.website.emp.control;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.szqj.service.domain.AbilityInfo;
import com.szqj.service.domain.AbilityInfoRepository;
import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.EnterpriseRepository;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.domain.DictRepository;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class EmpAbilityInfoControle {
	
	
	@Autowired
	private AbilityInfoRepository abilityInfoRepository;
	
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	
	@Autowired
	private DictRepository dictRepository;
	
	
	/**
	 * 产品信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/emp/abilityinfo.html"  )
	public String abilityinfo(@SessionAttribute Account account,  ModelMap modelMap){
		Enterprise enterprise = enterpriseRepository.findByAccountId(account.getAccountId()).get(0);
		List<AbilityInfo> list=abilityInfoRepository.findByEnterpriseId(enterprise.getEnterpriseId());
		modelMap.put("list", list);
		return "emp/abilityinfo"; 
	}
	
	
	
	@RequestMapping(value = "/emp/abilityinfo/add.html"  )
	public String abilityinfoAdd(@SessionAttribute Account account, ModelMap modelMap){
		Enterprise enterprise = enterpriseRepository.findByAccountId(account.getAccountId()).get(0);
		modelMap.put("abilityDocId", UUID.randomUUID().toString());
		modelMap.put("enterpriseId", enterprise.getEnterpriseId());
		modelMap.put("enterpriseName", enterprise.getEnterpriseName());
		
		
		return "emp/abilityinfo_add"; 
	}
	
	
	@RequestMapping(value = "/emp/abilityinfo/save.do"  )
	public String abilityinfoSave(AbilityInfo  ability,  ModelMap modelMap){
		ability.setCreateDate(new Date());
		ability.setState(1);
		abilityInfoRepository.save(ability);
		
		return "redirect:/emp/abilityinfo.html"; 
		
	}
	
	@RequestMapping(value = "/emp/abilityinfo/edit.html"  )
	public String abilityinfoEdit(String abilityId,  ModelMap modelMap){
		AbilityInfo ability = abilityInfoRepository.findById(abilityId).get();
		modelMap.put("abilityInfo", ability);
		return "emp/abilityinfo_edit"; 
	}
	
	
	@RequestMapping(value = "/emp/abilityinfo/view.html"  )
	public String abilityinfoView(String abilityId,  ModelMap modelMap){
		AbilityInfo ability = abilityInfoRepository.findById(abilityId).get();
		modelMap.put("ability", ability);
		return "emp/abilityinfo_view"; 
	}
	
	
	@RequestMapping(value = "/emp/abilityinfo/update.do"  )
	public String abilityinfoUpdate(AbilityInfo  ability,  ModelMap modelMap){
		AbilityInfo abilityRet = abilityInfoRepository.findById(ability.getAbilityInfoId()).get();
		Tools.copyBeanForUpdate(ability, abilityRet);
		abilityInfoRepository.save(abilityRet);
		return "redirect:/emp/abilityinfo.html"; 
		
	}
	
	@RequestMapping(value = "/emp/abilityinfo/del.do"  )
	public String abilityinfoDel(String  abilityId,  ModelMap modelMap){
		abilityInfoRepository.deleteById(abilityId);
		return "redirect:/emp/abilityinfo.html"; 
		
	}

}
