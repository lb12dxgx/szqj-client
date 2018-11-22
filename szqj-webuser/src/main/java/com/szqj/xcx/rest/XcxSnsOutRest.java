package com.szqj.xcx.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.sns.domain.Problem;
import com.szqj.sns.service.SnsService;
import com.szqj.util.RestJson;

@RestController
@RequestMapping("/xcx/out/sns/")
@EnableAutoConfiguration
public class XcxSnsOutRest {
	
	@Autowired
	private  SnsService  snsService;
	
	@RequestMapping(value = "getProblemByShareCode.xcx"  )
	public RestJson getProblemByShareCode(String shareCode){
		Problem problem = snsService.getProblemByShareCode(shareCode,null);
		return RestJson.createSucces(problem); 
	}

}
