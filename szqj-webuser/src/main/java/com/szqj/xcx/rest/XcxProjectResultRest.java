package com.szqj.xcx.rest;

import java.util.Date;
import java.util.List;

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
public class XcxProjectResultRest {
	
	@Autowired
	private BeforeProjectRepository beforeProjectRepository;
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	@Autowired
	private RegService regService;
	
	@Autowired
	ProjectResultRepository projectResultRepository;
	
	@RequestMapping(value = "/before/result/list.xcx"  )
	public RestJson list(@ModelAttribute("openid") String openid ){
		List<ProjectResult> l = projectResultRepository.findByOpenId(openid);
		return RestJson.createSucces(l);
	}
	
	@RequestMapping(value = "/before/result/listbyProject.xcx"  )
	public RestJson listbyProject(String beforeProjectId ){
		List<ProjectResult> l = projectResultRepository.findByBeforeProjectId(beforeProjectId);
		return RestJson.createSucces(l);
	}
	
	@RequestMapping(value = "/before/result/save.xcx"  )
	public RestJson save(ProjectResult projectResult,@ModelAttribute("openid") String openid,String beforeProjectId){
		
		Person person = regService.getPersonByOpenid(openid);
		String enterpriseId = person.getCompanyId();
		Enterprise enterprise = enterpriseRepository.findById(enterpriseId).get();
		BeforeProject beforeProject = beforeProjectRepository.findById(beforeProjectId).get();
		
		projectResult.setCreateDate(new Date());
		projectResult.setEnterpriseId(enterprise.getEnterpriseId());
		projectResult.setEnterpriseName(enterprise.getEnterpriseName());
		projectResult.setEnttelphone(enterprise.getEnttelphone());
		
		projectResult.setOpenid(openid);
		projectResult.setPersonId(person.getPersonId());
		projectResult.setPersonName(person.getPersonName());
		
		projectResult.setBeforeProjectId(beforeProjectId);
		projectResult.setProjectName(beforeProject.getProjectName());
		
		projectResultRepository.save(projectResult);
		return RestJson.createSucces(projectResult);
	}

}
