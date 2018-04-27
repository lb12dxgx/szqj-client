package com.szqj.before.rest;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.before.domain.ApplyOrg;
import com.szqj.before.domain.ApplyOrgRepository;
import com.szqj.before.service.ApplyOrgService;
import com.szqj.util.ConstantUtils;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;

@RestController
@RequestMapping("/system/applyorg")
@EnableAutoConfiguration
public class ApplyOrgRest {
	
	@Autowired
	private ApplyOrgRepository applyOrgRepository;
	
	@Autowired
	private ApplyOrgService applyOrgService;
	
	
	
	@RequestMapping(value = "list.do"  )
	public RestJson list(String orgName, Integer pageNum, Integer size){
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		Page<ApplyOrg> page =null;
		if(StringUtils.isBlank(orgName)){
			page = applyOrgRepository.findPage( pageable);
		}else{
			page = applyOrgRepository.findPageByOrgName(orgName, pageable);
		}
		return RestJson.createSucces(page);
	}
	
	
	
	@RequestMapping(value = "save.do"  )
	public RestJson save( ApplyOrg applyOrg){
		applyOrg.setPassword(ConstantUtils.ACCOUNT_DEFAULT_PASSWORD);
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
		applyOrgRepository.save(applyOrg);
		return RestJson.createSucces();
	}

}
