package com.szqj.website.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.szqj.reg.domain.RegInfo;
import com.szqj.reg.service.RegService;
import com.szqj.util.RestJson;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class BeforeControle {
	
	
	@Autowired
	private RegService regService;
	
	
	@RequestMapping(value = "/118/login.html"  )
	public String submit(ModelMap modelMap){
		return "118/login";
	}
	
	@RequestMapping(value = "/118/reg.html"  )
	public String reg(ModelMap modelMap){
		return "118/reg";
	}
	
	@RequestMapping(value = "/118/regTwo.html"  )
	public String regTwo(ModelMap modelMap){
		return "118/regTwo";
	}
	
	
	@RequestMapping(value = "/118/submitOne.html"  )
	public String submitOne(ModelMap modelMap){
		return "118/submitOne";
	}
	
	@RequestMapping(value = "/118/submitTwo.html"  )
	public String submitTwo(ModelMap modelMap){
		return "118/submitTwo";
	}
	
	@RequestMapping(value = "/118/submitThree.html"  )
	public String submitThree(ModelMap modelMap){
		return "118/submitThree";
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
	
	
	@RequestMapping(value = "/118/reg.do"  )
	public String reg(RegInfo regInfo,ModelMap modelMap){
		regService.regPerson(regInfo);
		modelMap.put("regInfo", regInfo);
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "getInviteCode.do"  )
	public RestJson getInviteCode(){
		String inviteCode = regService.genInviteCode(4);
		return RestJson.createSucces(inviteCode);
	}
	

}
