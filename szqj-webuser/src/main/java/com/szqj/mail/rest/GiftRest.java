package com.szqj.mail.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.mail.domain.Gift;
import com.szqj.mail.domain.GiftRepository;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;

@RestController
@RequestMapping("/system/mail/gift/")
@EnableAutoConfiguration
public class GiftRest {
	
	@Autowired
	private GiftRepository giftRepository;
	
	
	
	@RequestMapping(value = "list.do"  )
	public RestJson list( Integer pageNum, Integer size){
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		Page<Gift> page=giftRepository.findAll(pageable);
		return RestJson.createSucces(page);
	}
	
	@RequestMapping(value = "get.do"  )
	public RestJson get(String giftId){
		Gift gift = giftRepository.findById(giftId).get();
		return RestJson.createSucces(gift);
	}
	
	@RequestMapping(value = "save.do"  )
	public RestJson save(Gift gift){
		gift.setCreateDate(new Date());
		giftRepository.save(gift);
		return RestJson.createSucces(gift);
	}
	
	
	@RequestMapping(value = "update.do"  )
	public RestJson update( Gift gift){
		giftRepository.save(gift);
		return RestJson.createSucces(gift);
	}
	
	
	@RequestMapping(value = "delete.do"  )
	public RestJson delete( String giftId){
		giftRepository.deleteById(giftId);
		return RestJson.createSucces();
	}
	

}
