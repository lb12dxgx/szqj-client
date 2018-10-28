package com.szqj.mail.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.szqj.mail.domain.Exchange;
import com.szqj.mail.domain.ExchangeRepository;
import com.szqj.mail.domain.Gift;
import com.szqj.mail.domain.GiftRepository;
import com.szqj.mail.domain.MoneyRecordRepository;
import com.szqj.reg.service.RegService;
import com.szqj.service.domain.Person;

public class MailService {
	
	@Autowired
	private ExchangeRepository exchangeRepository;
	
	@Autowired
	private GiftRepository giftRepository;
	
	
	@Autowired
	private MoneyRecordRepository moneyRecordRepository;
	
	
	@Autowired
	private RegService regService;
	
	@Autowired
	private MoneyEventService moneyEventService;
	

    /**
     * 兑换奖品
     * @param openid
     * @param exchange
     * @return
     */
	public Exchange exchangeGift(String openid, Exchange exchange) {
		Person person = regService.getPersonByOpenid(openid);
		exchange.setCreateDate(new Date());
		exchange.setOpenid(openid);
		exchange.setPersonId(person.getPersonId());
		exchange.setMoney(getMoney(exchange));
		exchange.setExchangeCode(getExchangeCode(exchange));
		exchangeRepository.save(exchange);
		
		moneyEventService.send(openid,exchange);
		
		return exchange;
	}
	
	/**
	 * 按照年月日产生兑换码
	 * @param exchange
	 * @return
	 */
	private String getExchangeCode(Exchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 计算礼品价格
	 * @param exchange
	 * @return
	 */
	private Integer getMoney(Exchange exchange) {
		String giftId = exchange.getGiftId();
		Integer num=exchange.getNum();
		Gift gift = giftRepository.findById(giftId).get();
		return num*gift.getPrice();
	}
	

}
