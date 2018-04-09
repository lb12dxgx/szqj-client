package com.szqj.weborg.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.util.RestJson;
import com.szqj.weborg.service.OOSpiderService;

@RestController
@RequestMapping("/admin/internet/worm")
@EnableAutoConfiguration

public class OOSpiderRest {
	
	@Autowired
	private OOSpiderService ooSpiderService;
	
	@RequestMapping(value = "/start.do"  )
	public RestJson save(String Id){
		ooSpiderService.startSearch();
		return RestJson.createSucces();
	}

}
