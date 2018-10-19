package com.szqj.xcx.rest;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.before.domain.BeforeProject;
import com.szqj.before.domain.BeforeProjectRepository;
import com.szqj.before.domain.ProjectResult;
import com.szqj.before.domain.ProjectResultRepository;
import com.szqj.reg.service.RegService;
import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.EnterpriseRepository;
import com.szqj.service.domain.Person;
import com.szqj.util.RestJson;

@RestController
@RequestMapping("/xcx/login/")
@EnableAutoConfiguration
public class XcxBeforeProjectRest {
	
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	@Autowired
	private RegService regService;
	@Autowired
	private BeforeProjectRepository beforeProjectRepository;
	
	@Autowired
	private ProjectResultRepository projectResultRepository;
	
	@RequestMapping(value = "/before/applayproject/listByOpenId.xcx"  )
	public RestJson listByOpenId( @ModelAttribute("openid") String openid){
		List<BeforeProject> list = beforeProjectRepository.findByApplyOpenId(openid);
		for(BeforeProject project:list){
			addNum(project);
		}
		return RestJson.createSucces(list);
	}
	
	
	@RequestMapping(value = "/before/applayproject/listFinshByOpenId.xcx"  )
	public RestJson listFinshByOpenId( @ModelAttribute("openid") String openid){
		Person person = regService.getPersonByOpenid(openid);
		String enterpriseId = person.getCompanyId();
		List<BeforeProject> list = beforeProjectRepository.findByFinshApplyEnterpriseId(enterpriseId);
		for(BeforeProject project:list){
			addNum(project);
		}
		return RestJson.createSucces(list);
	}
	
	@RequestMapping(value = "/before/applayproject/listByCityId.xcx"  )
	public RestJson listByCityId( @ModelAttribute("openid") String openid){
		Person person = regService.getPersonByOpenid(openid);
		String enterpriseId = person.getCompanyId();
		Enterprise enterprise = enterpriseRepository.findById(enterpriseId).get();
		List<BeforeProject> list = beforeProjectRepository.findByApplyCityIdAndEnterpriseId(enterprise.getApplyCityId(),enterprise.getEnterpriseId());
		for(BeforeProject project:list){
			addNum(project);
		}
		return RestJson.createSucces(list);
	}
	
	
	@RequestMapping(value = "/before/applayproject/save.xcx"  )
	public RestJson save( BeforeProject beforeProject,@ModelAttribute("openid") String openid){
		Person person = regService.getPersonByOpenid(openid);
		String enterpriseId = person.getCompanyId();
		Enterprise enterprise = enterpriseRepository.findById(enterpriseId).get();
		
		beforeProject.setOpenid(openid);
		beforeProject.setEnttelphone(enterprise.getTelphone());
		beforeProject.setEnterpriseName(enterprise.getEnterpriseName());
		beforeProject.setEnterpriseId(enterprise.getEnterpriseId());
		beforeProject.setApplyCityId(enterprise.getApplyCityId());
		
		beforeProject.setPersonId(person.getPersonId());
		beforeProject.setPersonName(person.getPersonName());
		beforeProject.setTelePhone(person.getTelePhone());
		
		beforeProject.setCreateDate(new Date());
		
		beforeProjectRepository.save(beforeProject);
		return RestJson.createSucces(beforeProject);
	}
	
	@RequestMapping(value = "/before/applayproject/get.xcx"  )
	public RestJson get(String beforeProjectId){
		BeforeProject beforeProject = beforeProjectRepository.findById(beforeProjectId).get();
		return RestJson.createSucces(beforeProject);
	}
	
	
	@RequestMapping(value = "/before/applayproject/add.xcx"  )
	public RestJson add( Map<String,String> map){
		map.put("picId", UUID.randomUUID().toString());
		return RestJson.createSucces(map);
	}
	
	private void addNum(BeforeProject beforeProject){
		List<ProjectResult> l = projectResultRepository.findByBeforeProjectId(beforeProject.getBeforeProjectId());
		if(l==null||l.size()==0){
			beforeProject.setNum("无反馈");
		}else{
			beforeProject.setNum(l.size()+"家");
		}
	}

}