package com.szqj.xcx.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.mail.domain.Exchange;
import com.szqj.mail.domain.ExchangeRepository;
import com.szqj.mail.domain.Gift;
import com.szqj.mail.domain.GiftRepository;
import com.szqj.mail.service.MailService;
import com.szqj.reg.service.RegService;
import com.szqj.service.domain.Person;
import com.szqj.util.RestJson;

@RestController
@RequestMapping("/xcx/login/mail/")
@EnableAutoConfiguration
public class XcxMailRest {
	
	@Autowired
	private GiftRepository giftRepository;
	
	@Autowired
	private ExchangeRepository exchangeRepository;
	
	@Autowired
	private RegService regService;
	
	@Autowired
	private MailService mailService;
	
	
	@RequestMapping(value = "/giftList.xcx"  )
	public RestJson giftList(){
		List<Gift> list = giftRepository.findSellGift();
		return RestJson.createSucces(list);
	}
	
	
	@RequestMapping(value = "/getGift.xcx"  )
	public RestJson getGift(String giftId){
		Gift  gift = giftRepository.findById(giftId).get();
		return RestJson.createSucces(gift);
	}
	
	
	@RequestMapping(value = "/exchangeGift.xcx"  )
	public RestJson exchangeGift( @ModelAttribute("openid") String openid ,Exchange exchange){
		Exchange retexchange= mailService.exchangeGift(openid,exchange); 
		if(retexchange==null){
			return RestJson.createError();
		}
		return RestJson.createSucces(retexchange);
	}
	

	@RequestMapping(value = "/exchangeList.xcx"  )
	public RestJson exchangeList(@ModelAttribute("openid") String openid ){
		List<Exchange> list = exchangeRepository.findByOpenId(openid);
		return RestJson.createSucces(list);
	}
	

}
