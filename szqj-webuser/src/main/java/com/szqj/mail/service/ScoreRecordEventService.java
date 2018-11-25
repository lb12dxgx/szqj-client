package com.szqj.mail.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szqj.mail.domain.Exchange;
import com.szqj.mail.domain.ScoreRecord;
import com.szqj.mail.domain.ScoreRecordRepository;
import com.szqj.service.domain.MyPersonRepository;
import com.szqj.service.domain.Person;
import com.szqj.sns.domain.Result;

@Service
@Transactional
public class ScoreRecordEventService {
	
	@Autowired
	private ScoreRecordRepository scoreRecordRepository;
	
	@Autowired
	private MyPersonRepository personRepository;
	
	
	/**
	 * 根据回复情况添加积分
	 * @param openid
	 * @param exchange
	 */
	public void sendByResult(Result result) {
		ScoreRecord scoreRecord=new ScoreRecord();
		scoreRecord.setBusinessId(result.getResultId());
		scoreRecord.setBusinessName("result");
		scoreRecord.setBusinessType(0);
		scoreRecord.setCreateDate(new Date());
		scoreRecord.setNum(result.getScoreNum());
		scoreRecord.setOpenid(result.getOpenid());
		scoreRecord.setPersonId(result.getPersonId());
		scoreRecord.setPersonName(result.getPersonName());
		scoreRecordRepository.save(scoreRecord);
		updatePerson(result.getPersonId());
	}
	
	private void updatePerson(String personId) {
		Person person = personRepository.findById(personId).get();
		Integer scoreSum=scoreRecordRepository.findSumByPersonId(personId);
		person.setScore(scoreSum);
		personRepository.save(person);
	}


}
