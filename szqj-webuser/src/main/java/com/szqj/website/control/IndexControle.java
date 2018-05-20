package com.szqj.website.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.szqj.before.domain.ApplyOrg;
import com.szqj.before.domain.ApplyOrgRepository;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class IndexControle {
	
	
	@Autowired
	private ApplyOrgRepository applyOrgRepository;
	
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
		List<ApplyOrg> l = applyOrgRepository.findAllList();
		modelMap.put("orgList", l);
		return "118/index"; 
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
	

}
