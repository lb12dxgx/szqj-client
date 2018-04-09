package com.szqj.weborg.rest;

import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.util.RestJson;
import com.szqj.weborg.domain.Org;
import com.szqj.weborg.domain.OrgRepository;



@RestController
@RequestMapping("/system/org/")
@EnableAutoConfiguration
public class OrgRest {
	
	@Autowired
	private OrgRepository orgRepository;
	
	
	//根据账号类型返回账号列表
	@RequestMapping(value = "save.do"  )
	public RestJson save(@RequestBody String body ){
		Org org = orgRepository.findAll().get(0);
		//orgJson=orgJson.replaceAll("\\", "");
		org.setOrgJsonTree(body);
		orgRepository.save( org);
		return RestJson.createSucces(org);
	}
	
	
	//保存账号信息
	@RequestMapping(value = "tree.do"  )
	public RestJson tree(){
		List<Org> l = orgRepository.findAll();
		if(l==null||l.size()==0){
			Org o=new Org();
			o.setOrgJsonTree("");
			orgRepository.save(o);
		}
		Org org = orgRepository.findAll().get(0);
		
		return RestJson.createSucces(org.getOrgJsonTree());
	}
	
	
		
	
	
	

}
