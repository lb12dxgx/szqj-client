package com.szqj.mail.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szqj.mail.domain.Exchange;
import com.szqj.sns.domain.Problem;


@Service
@Transactional
public class MoneyEventService {

	
	/**
	 * 处理兑换奖励
	 * @param openid
	 * @param exchange
	 */
	public void send(String openid, Exchange exchange) {
		// TODO Auto-generated method stub
		
	}
	

}
