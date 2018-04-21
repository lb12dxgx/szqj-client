package com.szqj.website.control;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class IndexControle {
	
	
	/**
	 * 首页
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/"  )
	public String index(ModelMap modelMap){
		return "index";
	}
	
	
	/**
	 * 电子期刊
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/login/login.html"  )
	public String login(ModelMap modelMap){
		return "login/login"; 
	}
	
	
	
	

	/**
	 * 118服务
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/118/index.html"  )
	public String index_118(ModelMap modelMap){
		return "118/index"; 
	}
	
	
	/**
	 * 管线安全
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/accident/index.html"  )
	public String index_accident(ModelMap modelMap){
		return "accident/index"; 
	}
	
	
	/**
	 * 产业资讯
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/news/index.html"  )
	public String index_news(ModelMap modelMap){
		return "news/index"; 
	}
	
	

	/**
	 * 产业培训
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/train/index.html"  )
	public String index_train(ModelMap modelMap){
		return "train/index"; 
	}
	
	
	/**
	 * 产业服务
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/service/index.html"  )
	public String index_service(ModelMap modelMap){
		return "service/index"; 
	}
	
	/**
	 * 电子期刊
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/maga/index.html"  )
	public String index_maga(ModelMap modelMap){
		return "maga/index"; 
	}
	
	

	

}
