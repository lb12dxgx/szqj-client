package com.szqj.service.rest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.EnterpriseCert;
import com.szqj.service.domain.EnterpriseCertRepository;
import com.szqj.service.domain.EnterpriseRepository;
import com.szqj.train.domain.TrainCert;
import com.szqj.train.domain.TrainCertRepository;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;



@RestController
@RequestMapping("/system/enterprisecert/")
@EnableAutoConfiguration
public class  EnterpriseCertRest {
	
	@Autowired
	private  EnterpriseCertRepository  enterpriseCertRepository;
	
	@Autowired
	private  EnterpriseRepository  enterpriseRepository;
	
	
	
	@RequestMapping(value = "list.do"  )
	
	public RestJson list( String enterpriseId){
		List<EnterpriseCert> list = enterpriseCertRepository.findByEnterpriseId(enterpriseId);
		return RestJson.createSucces(list);
		
		
	}
	
	@RequestMapping(value = "get.do"  )
	public RestJson get(String enterpriseCertId){
		EnterpriseCert enterpriseCert = enterpriseCertRepository.findById(enterpriseCertId).get();
		return RestJson.createSucces(enterpriseCert);
	}
	
	@RequestMapping(value = "save.do"  )
	public RestJson save( EnterpriseCert enterpriseCert){
		Enterprise enterprise = enterpriseRepository.findById(enterpriseCert.getEnterpriseId()).get();
		enterpriseCert.setCreateDate(new Date());
		enterpriseCert.setEnterpriseName(enterprise.getEnterpriseName());
		enterpriseCertRepository.save(enterpriseCert);
		return RestJson.createSucces(enterpriseCert);
	}
	
	
	@RequestMapping(value = "update.do"  )
	public RestJson update( EnterpriseCert enterpriseCert){
		enterpriseCertRepository.save(enterpriseCert);
		return RestJson.createSucces(enterpriseCert);
	}
	
	
	@RequestMapping(value = "delete.do"  )
	public RestJson delete( String enterpriseCertId){
		enterpriseCertRepository.deleteById(enterpriseCertId);
		return RestJson.createSucces();
	}
	
	
		
	
	
	

}
