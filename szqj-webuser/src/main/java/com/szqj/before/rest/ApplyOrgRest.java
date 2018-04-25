package com.szqj.before.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.before.domain.ApplyOrg;
import com.szqj.before.domain.ApplyOrgRepository;
import com.szqj.before.service.ApplyOrgService;
import com.szqj.util.ConstantUtils;
import com.szqj.util.RestJson;

@RestController
@RequestMapping("/system/applyorg")
@EnableAutoConfiguration
public class ApplyOrgRest {
	
	@Autowired
	private ApplyOrgRepository applyOrgRepository;
	
	@Autowired
	private ApplyOrgService applyOrgService;
	
	
	
	@RequestMapping(value = "list.do"  )
	public RestJson list(){
		 List<ApplyOrg> l = applyOrgRepository.findAll();
		return RestJson.createSucces(l);
	}
	
	
	
	@RequestMapping(value = "save.do"  )
	public RestJson save( ApplyOrg applyOrg){
		applyOrgService.saveApplyOrg(applyOrg);
		return RestJson.createSucces(applyOrg);
	}
	
	
	@RequestMapping(value = "update.do"  )
	public RestJson update(ApplyOrg applyOrg){
		applyOrgRepository.save(applyOrg);
		return RestJson.createSucces(applyOrg);
	}
	
	
	@RequestMapping(value = "delete.do"  )
	public RestJson delete( String applyOrgId){
		ApplyOrg applyOrg = applyOrgRepository.findById(applyOrgId).get();
		applyOrg.setState(ConstantUtils.ACCOUNT_STATE_DEL);
		applyOrgService.saveApplyOrg(applyOrg);
		return RestJson.createSucces();
	}

}
