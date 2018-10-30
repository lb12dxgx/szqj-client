package com.szqj.mail.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szqj.mail.domain.Exchange;
import com.szqj.mail.domain.ExchangeRepository;
import com.szqj.mail.domain.Gift;
import com.szqj.mail.domain.GiftRepository;
import com.szqj.mail.domain.ScoreRecord;
import com.szqj.mail.domain.ScoreRecordRepository;
import com.szqj.reg.service.RegService;
import com.szqj.service.domain.MyPersonRepository;
import com.szqj.service.domain.Person;
import com.szqj.sns.domain.Problem;


@Service
@Transactional
public class MoneyEventService {

	@Autowired
	private ScoreRecordRepository scoreRecordRepository;
	
	@Autowired
	private RegService regService;
	
	@Autowired
	private MyPersonRepository personRepository;
	
	
	@Autowired
	private GiftRepository giftRepository;
	
	/**
	 * 处理兑换奖励
	 * @param openid
	 * @param exchange
	 */
	public void send(String openid, Exchange exchange) {
		Person person = regService.getPersonByOpenid(openid);
		addScoreRecord(openid, exchange, person);
		updatePerson(openid, exchange, person);
		setGiftNum(exchange);
	}


	private void updatePerson(String openid, Exchange exchange, Person person) {
		Integer scoreSum=scoreRecordRepository.findSumByOpenid(openid);
		person.setScore(scoreSum);
		person.setPostAddren(exchange.getPostAddren());
		personRepository.save(person);
	}


	private void addScoreRecord(String openid, Exchange exchange, Person person) {
		ScoreRecord scoreRecord=new ScoreRecord();
		scoreRecord.setBusinessName("礼物兑换");
		scoreRecord.setBusinessType(1);
		scoreRecord.setBusinessId(exchange.getExchangeId());
		scoreRecord.setNum(0-exchange.getMoney());
		scoreRecord.setOpenid(openid);
		scoreRecord.setPersonId(person.getPersonId());
		scoreRecord.setPersonName(person.getPersonName());
		scoreRecord.setCreateDate(new Date());
		scoreRecordRepository.save(scoreRecord);
	}
	
	
	private void setGiftNum(Exchange exchange) {
		String giftId = exchange.getGiftId();
		Integer num=exchange.getNum();
		Gift gift = giftRepository.findById(giftId).get();
		int n = gift.getNum()-num;
		gift.setNum(n);
		giftRepository.save(gift);
		
	}
	

}
