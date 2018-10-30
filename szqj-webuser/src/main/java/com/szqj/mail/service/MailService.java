package com.szqj.mail.service;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szqj.mail.domain.Exchange;
import com.szqj.mail.domain.ExchangeRepository;
import com.szqj.mail.domain.Gift;
import com.szqj.mail.domain.GiftRepository;
import com.szqj.reg.service.RegService;
import com.szqj.service.domain.Person;


@Service
@Transactional
public class MailService { 
	
	@Autowired
	private ExchangeRepository exchangeRepository;
	
	@Autowired
	private GiftRepository giftRepository;
	
	@Autowired
	private RegService regService;
	
	@Autowired
	private MoneyEventService moneyEventService;
	

	/**
	 * 判断数量
	 * @param exchange
	 * @return
	 */
	private boolean validateNum(Exchange exchange){
		String giftId = exchange.getGiftId();
		Integer num=exchange.getNum();
		Gift gift = giftRepository.findById(giftId).get();
		if(num>gift.getNum()){
			return false;
		}
		
		return true;
		
	};
	
	/**
	 * 判断金额
	 * @param exchange
	 * @return
	 */
	private boolean validateMoney(String openid, Exchange exchange){
		Person person = regService.getPersonByOpenid(openid);
		String giftId = exchange.getGiftId();
		Integer num=exchange.getNum();
		Gift gift = giftRepository.findById(giftId).get();
		Integer score=num*gift.getPrice();
		if(score>person.getScore()){
			return false;
		}
		return true;
		
	}
	
	
	
	
    /**
     * 兑换奖品
     * @param openid
     * @param exchange
     * @return
     */
	public Exchange exchangeGift(String openid, Exchange exchange) {
		if(validateNum(exchange)&&validateMoney(openid,exchange)){
			Person person = regService.getPersonByOpenid(openid);
			exchange.setCreateDate(new Date());
			exchange.setOpenid(openid);
			exchange.setPersonId(person.getPersonId());
			exchange.setMoney(getMoney(exchange));
			genExchangeCode(exchange);
			exchangeRepository.save(exchange);
			moneyEventService.send(openid,exchange);
			return exchange;
		}
		return null;
	}
	
	/**
	 * 按照年月日产生兑换码
	 * @param exchange
	 * @return
	 */
	private void genExchangeCode(Exchange exchange) {
		Calendar calendar = Calendar.getInstance();
		
		int year = calendar.get(Calendar.YEAR);//得到年份
        int month = calendar.get(Calendar.MONTH)+1;//得到月份
        int day = calendar.get(Calendar.DATE);//得到月份中今天的号数
		 java.util.List<Exchange> exchangeList = exchangeRepository.findBYYearAndMonthAndDay(year,month,day);
		if(exchangeList==null||exchangeList.size()==0){
			
		}
		
		
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
