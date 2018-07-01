package com.szqj.website.emp.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.EnterpriseRepository;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class EmpControle {
	
	
	
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	
	
	
	/**
	 * 企业信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/emp/info.html"  )
	public String info(ModelMap modelMap,HttpServletRequest request){
		Account account = (Account)request.getSession().getAttribute("account");
		List<Enterprise> enterprises = enterpriseRepository.findByAccountId(account.getAccountId());
		modelMap.put("enterprise", enterprises.get(0));
		return "emp/info"; 
	}
	
	
	/**
	 * 企业信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/emp/save.do"  )
	public String save(Enterprise enterprise, ModelMap modelMap){
		Enterprise enterpriseRet = enterpriseRepository.findById(enterprise.getEnterpriseId()).get();
		Tools.copyBeanForUpdate(enterprise, enterpriseRet);
		enterpriseRepository.save(enterpriseRet);
		return "redirect:emp/info"; 
	}
	
	
}
