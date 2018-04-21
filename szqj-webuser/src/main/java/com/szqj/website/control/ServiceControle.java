package com.szqj.website.control;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class ServiceControle {
	

	
	
	/**
	 * 电子证书认证
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/service/reg.html"  )
	public String reg(ModelMap modelMap){
		return "service/reg";
	}
	
	/**
	 * 电子证书认证
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/service/regResult.html"  )
	public String regResult(ModelMap modelMap){
		return "service/regResult";
	}
	
	
	/**
	 * 电子证书查询
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/service/cardSearch.html"  )
	public String cardSearch(ModelMap modelMap){
		return "service/cardSearch"; 
	}
	
	
	/**
	 * 电子证书查询结果
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/service/cardResult.html"  )
	public String cardResult(ModelMap modelMap){
		return "service/cardResult"; 
	}
	
	
	/**
	 * 会议展览
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/service/meet.html"  )
	public String index_118(ModelMap modelMap){
		return "service/meet"; 
	}
	
	
	/**
	 * 技术推广
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/service/technology.html"  )
	public String index_accident(ModelMap modelMap){
		return "service/technology"; 
	}
	
	
	/**
	 * 产业资讯
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/service/finace.html"  )
	public String index_news(ModelMap modelMap){
		return "service/finace"; 
	}
	
	

	

	

}
