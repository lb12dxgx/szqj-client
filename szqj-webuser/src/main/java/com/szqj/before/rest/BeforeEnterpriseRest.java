package com.szqj.before.rest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.EnterpriseRepository;
import com.szqj.service.domain.MyPersonRepository;
import com.szqj.service.domain.Person;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.domain.AccountRepository;

@RestController
@RequestMapping("/system/beforeenterpriserest")
@EnableAutoConfiguration
public class BeforeEnterpriseRest{

	@Autowired
	private EnterpriseRepository enterpriseRepository;



	// 根据企业ID返回人员列表
	@RequestMapping(value = "listByApplyCityId.do")
	public RestJson list(String enterpriseName,String applyCityId, Integer enterpriseType, Integer pageNum, Integer size) {
		PageRequest pageable = Tools.getPage(pageNum - 1, size);
		Page<Enterprise> page = null;
		if (StringUtils.isBlank(enterpriseName)) {
			page = enterpriseRepository.findByApplyCityIdAndEnterpriseTypeAndName(applyCityId,enterpriseName, enterpriseType,pageable);
		} else {
			page = enterpriseRepository.findByApplyCityIdAndEnterpriseType(applyCityId,enterpriseType,pageable);
		}
		return RestJson.createSucces(page);
	}


	// 修改人员所属企业
	@RequestMapping(value = "changeCompany.do")
	public RestJson changeApplyCityId(String enterpriseId) {
		Enterprise enterprise = enterpriseRepository.findById(enterpriseId).get();
		enterprise.setApplyCityId("");
		enterpriseRepository.save(enterprise);
		return RestJson.createSucces(enterprise);
	}

}
