package com.szqj.cms.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.cms.domain.OutNewsInfo;
import com.szqj.cms.domain.OutNewsInfoRepository;
import com.szqj.util.RestJson;



@RestController
@RequestMapping("/system/outnews/")
@EnableAutoConfiguration
public class OutNewsRest {
	
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private  OutNewsInfoRepository  outNewsInfoRepository;
	
	
	
	//根据栏目返回内容列表
	@RequestMapping(value = "listByKeWord.do"  )
	public RestJson listByKeWord( String keyword, Integer state){
		List<OutNewsInfo> list = outNewsInfoRepository.findByKeywordAndState(keyword, state);
		return RestJson.createSucces(list);
	}
	
	//根据栏目返回内容列表
	@RequestMapping(value = "list.do"  )
	public RestJson list(){
		List<OutNewsInfo> list = outNewsInfoRepository.findByState();
		return RestJson.createSucces(list);
}
	
	
	//保存账号信息
	@RequestMapping(value = "view.do"  )
	public RestJson save(String outnewsinfoId){
		OutNewsInfo outNewsInfo = outNewsInfoRepository.findOne(outnewsinfoId);
		outNewsInfo.setState(1);
		outNewsInfoRepository.save(outNewsInfo);
		return RestJson.createSucces(outNewsInfo);
	}
	
	
	
	//删除
	@RequestMapping(value = "delete.do"  )
	public RestJson delete( String outnewsinfoId){
		outNewsInfoRepository.delete(outnewsinfoId);
		return RestJson.createSucces();
	}
	
	
	
	
		
	
	
	

}
