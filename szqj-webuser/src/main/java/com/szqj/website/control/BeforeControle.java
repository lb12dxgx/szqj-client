package com.szqj.website.control;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.szqj.before.domain.ApplyOrg;
import com.szqj.before.domain.ApplyOrgRepository;
import com.szqj.before.domain.BeforeApply;
import com.szqj.before.domain.BeforeApplyRepository;
import com.szqj.before.service.BeforeApplyService;
import com.szqj.company.domain.Company;
import com.szqj.company.domain.CompanyRepository;
import com.szqj.reg.domain.RegInfo;
import com.szqj.reg.service.RegService;
import com.szqj.springmvc.Token;
import com.szqj.util.RestJson;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.service.AccountService;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class BeforeControle {
	
	
	@Autowired
	private RegService regService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private BeforeApplyRepository beforeApplyRepository;
	
	@Autowired
	private BeforeApplyService beforeApplyService;
	
	@Autowired
	private ApplyOrgRepository applyOrgRepository;
	
	@Autowired 
	private CompanyRepository companyRepository;
	
	
	@RequestMapping(value = "/118/login.html"  )
	public String login(String applyOrgId,ModelMap modelMap){
		List<ApplyOrg> l = applyOrgRepository.findAllList();
		modelMap.put("orgList", l);
		modelMap.put("applyOrgId",applyOrgId);
		
		return "118/login";
	}
	
	@Token(save = true)
	@RequestMapping(value = "/118/reg.html"  )
	public String reg(ModelMap modelMap){
		return "118/reg";
	}
	
	
	
	@RequestMapping(value = "/118/search.html"  )
	public String search(ModelMap modelMap){
		return "118/search";
	}
	
	
	@RequestMapping(value = "/118/resultSucess.html"  )
	public String resultSucess(ModelMap modelMap){
		return "118/resultSucess";
	}
	
	@RequestMapping(value = "/118/resultFail.html"  )
	public String resultFail(ModelMap modelMap){
		return "118/resultFail";
	}
	
	
	
	@RequestMapping(value = "/118/about.html"  )
	public String about(ModelMap modelMap){
		return "118/about";
	}
	
	@ResponseBody
	@RequestMapping(value = "/118/getOrgList.do"  )
	public RestJson getOrgList(){
		List<ApplyOrg> l = applyOrgRepository.findAllList();
		return RestJson.createSucces(l);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/118/getSmsCode.do"  )
	public RestJson getSmsCode(RegInfo regInfo){
		RegInfo regInfoRet = regService.genSmsCode(regInfo,4);
		return RestJson.createSucces(regInfoRet.getSmscode());
	}
	
	@Token(save = true)
	@RequestMapping(value = "/118/login.do"  )
	public String login(String userName ,String password,String applyOrgId,ModelMap modelMap,RedirectAttributes attributes){
		Account account = accountService.login(userName, password);
		account.setToken(account.getAccountId());
		if(StringUtils.isBlank(account.getLoginStr())){
			
			 ApplyOrg applyOrg = applyOrgRepository.findById(applyOrgId).get();
			 Company company = companyRepository.findByAccountId(account.getAccountId());
			 
			 modelMap.put("account", account);
			 modelMap.put("applyOrgId", applyOrgId);
			 modelMap.put("companyId",company.getCompanyId());
			 if(("com").equals(applyOrg.getFormCode())) {
				 return "118/submitTwo";
			 }else {
				 return "118/submitTwo"+applyOrg.getFormCode();
			 }
		}else{
			 modelMap.put("loginStr", account.getLoginStr());
			 modelMap.put("applyOrgId", applyOrgId);
			 return "118/login";
		}
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/118/validateSmsCode.do"  )
	public RestJson validateSmsCode(RegInfo regInfo){
		boolean flag = regService.validateSmsCode(regInfo);
		return RestJson.createSucces(!flag);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/118/isExitByTelphone.do"  )
	public boolean isExitByTelphone(RegInfo regInfo){
		boolean flag = regService.isExitByTelphone(regInfo);
		return !flag;
	}
	
	@Token(remove =true,save = true)
	@RequestMapping(value = "/118/regSubmit.do"  )
	public String regSubmit(RegInfo regInfo,ModelMap modelMap){
		RegInfo reg=regService.regBeforeUser(regInfo);
		modelMap.put("regInfo", reg);
		return "118/regTwo";
	}
	
	@Token(remove =true)
	@RequestMapping(value = "/118/regComapnySubmit.do"  )
	public String regComapnySubmit(String reginfoId,Company company,ModelMap modelMap){
		RegInfo reg=regService.regComapnySubmit(reginfoId,company);
		modelMap.put("regInfo", reg);
		return "118/login";
	}
	
	@Token(remove =true)
	@RequestMapping(value = "/118/applySubmit.do"  )
	public String applySubmit(BeforeApply beforeApply,ModelMap modelMap){
		beforeApplyService.create(beforeApply);
		ApplyOrg applyOrg = applyOrgRepository.findById(beforeApply.getApplyOrgId()).get();
		String companyId = beforeApply.getCompanyId();
		Company company = companyRepository.findById(companyId).get();
		modelMap.put("beforeApply", beforeApply);
		modelMap.put("applyOrg", applyOrg);
		modelMap.put("company", company);
		
		return "118/submitThree";
	}
	
	
	
	

	

}
