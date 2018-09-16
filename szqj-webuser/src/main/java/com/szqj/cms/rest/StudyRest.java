package com.szqj.cms.rest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.util.RestJson;

@RestController
@RequestMapping("/system/study")
@EnableAutoConfiguration
public class StudyRest {
	
	
	@RequestMapping(value = "/oneStep.do"  )
	public RestJson oneStep(Integer bigNum, Integer smalNum) {
		Integer num=bigNum-10;
		return RestJson.createSucces("第一步："+bigNum+"=10+"+num);
	}
	
	@RequestMapping(value = "/twoStep.do"  )
	public RestJson twoStep(Integer bigNum, Integer smalNum) {
		Integer num=bigNum-10;
		if(num>smalNum) {
			return RestJson.createSucces("第二步："+num+"大于"+smalNum);
			
		}else {
			return RestJson.createSucces("第二步："+num+"小于"+smalNum);
		}
		
	}
	
	
	@RequestMapping(value = "/threeStep.do"  )
	public RestJson threeStep(Integer bigNum, Integer smalNum) {
		Integer num=bigNum-10;
		if(num>smalNum) {
			return RestJson.createSucces("第三步："+num+"-"+smalNum+"="+(num-smalNum));
			
		}else {
			return RestJson.createSucces("第三步："+10+"-"+smalNum+"="+(10-smalNum));
		}
		
	}
	
	
	@RequestMapping(value = "/foreStep.do"  )
	public RestJson foreStep(Integer bigNum, Integer smalNum) {
		Integer num=bigNum-10;
		if(num>smalNum) {
			return RestJson.createSucces("第四步："+10+"+"+(num-smalNum)+"="+(10+(num-smalNum)));
			
		}else {
			int n = (10-smalNum);
			return RestJson.createSucces("第四步："+n+"+"+num+"="+(bigNum-smalNum));
		}
		
	}


}
