package com.szqj.website.emp.control;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.szqj.service.domain.BuyInfoRepository;
import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.EnterpriseRepository;
import com.szqj.service.domain.BuyInfo;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.domain.Dict;
import com.szqj.weborg.domain.DictRepository;


@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class EmpBuyInfoControle {
	
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	
	@Autowired
	private DictRepository dictRepository;
	
	@Autowired
	private BuyInfoRepository buyInfoRepository;
	
	/**
	 * 供求信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/emp/buyinfo.html"  )
	public String buyinfo(@SessionAttribute Account account,Integer pageNum, Integer size, String buyInfoName, ModelMap modelMap){
		PageRequest pageable=Tools.getPage(pageNum, 6);
		Page<BuyInfo> page=null;
		Enterprise enterprise = enterpriseRepository.findByAccountId(account.getAccountId()).get(0);
		if(StringUtils.isBlank(buyInfoName)){
			page=buyInfoRepository.findPageByEnterpriseId(enterprise.getEnterpriseId(),pageable);
		}else{
			page=buyInfoRepository.findPageByBuyInfoName(buyInfoName,enterprise.getEnterpriseId(),pageable);
		}
		modelMap.put("page", page);
		modelMap.put("buyInfoName", buyInfoName);
	
		return "emp/buyinfo"; 
	}
	
	
	
	@RequestMapping(value = "/emp/buyinfo/add.html"  )
	public String buyinfoAdd(@SessionAttribute Account account, ModelMap modelMap){
		Enterprise enterprise = enterpriseRepository.findByAccountId(account.getAccountId()).get(0);
		modelMap.put("enterpriseId", enterprise.getEnterpriseId());
		modelMap.put("empName", enterprise.getEnterpriseName());
		return "emp/buyinfo_add"; 
	}
	
	
	@RequestMapping(value = "/emp/buyinfo/save.do"  )
	public String buyinfoSave(BuyInfo  buyInfo,  ModelMap modelMap){
		List<Dict> dicts = dictRepository.findByDictValue(buyInfo.getBuyInfoTypeCode());
		buyInfo.setBuyInfoType(dicts.get(0).getDictName());
		buyInfoRepository.save(buyInfo);
		
		return "redirect:/emp/buyinfo.html"; 
		
	}
	
	@RequestMapping(value = "/emp/buyinfo/edit.html"  )
	public String buyinfoEdit(String buyInfoId,  ModelMap modelMap){
		BuyInfo buyInfo = buyInfoRepository.findById(buyInfoId).get();
		List<Dict> dicts = dictRepository.findByDictValue(buyInfo.getBuyInfoTypeCode());
		Dict pdict = dictRepository.findById(dicts.get(0).getPdictId()).get();
		modelMap.put("buyInfo", buyInfo);
		modelMap.put("ptypeCode", pdict.getDictValue());
		return "emp/buyinfo_edit"; 
	}
	
	
	@RequestMapping(value = "/emp/buyinfo/update.do"  )
	public String buyinfoUpdate(BuyInfo  buyInfo,  ModelMap modelMap){
		BuyInfo buyInfoRet = buyInfoRepository.findById(buyInfo.getBuyInfoId()).get();
		Tools.copyBeanForUpdate(buyInfo, buyInfoRet);
		buyInfoRepository.save(buyInfoRet);
		return "redirect:/emp/buyinfo.html"; 
		
	}
	
	@RequestMapping(value = "/emp/buyinfo/del.do"  )
	public String buyinfoDel(String  buyInfoId,  ModelMap modelMap){
		buyInfoRepository.deleteById(buyInfoId);
		return "redirect:/emp/buyinfo.html"; 
		
	}


}
