package com.szqj.mail.rest;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.mail.domain.Exchange;
import com.szqj.mail.domain.ExchangeRepository;
import com.szqj.mail.domain.Gift;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;



/**
 * 兑换记录
 * @author libingbing
 *
 */
@RestController
@RequestMapping("/system/mail/exchange/")
@EnableAutoConfiguration
public class ExchangeRest {
	
	@Autowired
	private ExchangeRepository exchangeRepository;
	
	
	@RequestMapping(value = "list.do"  )
	public RestJson list(String exchangeCode,String personName, Integer pageNum, Integer size){
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		Page<Exchange> page=null;
		if(StringUtils.isNotBlank(exchangeCode)&&StringUtils.isNotBlank(personName)){
			page=exchangeRepository.findByExchangeCodeAndPersonName(exchangeCode,personName,0,pageable);
		}else if(StringUtils.isNotBlank(exchangeCode)){
			page=exchangeRepository.findByExchangeCode(exchangeCode,0,pageable);
		}else if(StringUtils.isNotBlank(personName)){
			page=exchangeRepository.findByPersonName(personName,0,pageable);
		}else{
			page=exchangeRepository.findUnFinsh(pageable);
		}
		
		return RestJson.createSucces(page);
	}
	
	
	@RequestMapping(value = "finshlist.do"  )
	public RestJson finshlist(String exchangeCode,String personName, Integer pageNum, Integer size){
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		Page<Exchange> page=null;
		if(StringUtils.isNotBlank(exchangeCode)&&StringUtils.isNotBlank(personName)){
			page=exchangeRepository.findByExchangeCodeAndPersonName(exchangeCode,personName,1,pageable);
		}else if(StringUtils.isNotBlank(exchangeCode)){
			page=exchangeRepository.findByExchangeCode(exchangeCode,1,pageable);
		}else if(StringUtils.isNotBlank(personName)){
			page=exchangeRepository.findByPersonName(personName,1,pageable);
		}else{
			page=exchangeRepository.findFinsh(pageable);
		}
		
		return RestJson.createSucces(page);
	}
	
	
	@RequestMapping(value = "addPost.do")
	public RestJson addPost(String exchangeId,String postCode,Date postDate){
		Exchange exchange = exchangeRepository.findById(exchangeId).get();
		exchange.setPostCode(postCode);
		exchange.setPostDate(postDate);
		exchangeRepository.save(exchange);
		return RestJson.createSucces(exchange);
	}
	
	

}
