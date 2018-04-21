package com.szqj.weborg.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.util.RestJson;
import com.szqj.weborg.domain.Menu;
import com.szqj.weborg.domain.MenuRepository;
import com.szqj.weborg.rest.vo.MenuNode;
import com.szqj.weborg.service.MenuService;

import java.util.List;



@RestController
@RequestMapping("/system/menu")
@EnableAutoConfiguration
public class MenuRest {
	
	

	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value = "/save.do"  )
	public RestJson save(Menu menu){
		menuRepository.save(menu);
		return RestJson.createSucces(menu);
	}
	
	
	@RequestMapping(value = "/update.do"  )
	public RestJson update(Menu menu){
		menuRepository.save(menu);
		return RestJson.createSucces(menu);
	}
	
	@RequestMapping(value = "/get.do"  )
	public RestJson get(String  menuId){
		Menu menu = menuRepository.findById(menuId).get();
		return RestJson.createSucces(menu);
	}
	
	
	@RequestMapping(value = "/delete.do"  )
	public RestJson delete(String  menuId){
	     menuRepository.deleteById(menuId);
		return RestJson.createSucces(menuId);
	}
	 
	
	@RequestMapping(value = "/list.do"  )
	public RestJson list(String  menuId){
		 List<Menu> list = menuRepository.findAll();
		 return RestJson.createSucces(list);
	}
	
	
	@RequestMapping(value = "/tree.do"  )
	public RestJson tree(){
		List<MenuNode> list = menuService.getMenuTree();
		return RestJson.createSucces(list);
	}
	
	
	



}
