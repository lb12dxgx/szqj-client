package com.szqj.weborg.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szqj.mail.service.PayService;
import com.szqj.weborg.service.IndexService;

@Service
@Transactional
public class ScheduledService {
	
	@Autowired
	private IndexService indexService;
	
	@Autowired
	private PayService payService;
	
	/**
	 * 每5分钟生产一次首页面
	 * 
	 */
	//@Scheduled(cron = "0 */5 *  * * * ")
    public void genIndexHtml() { 
		indexService.createHtml();
    }
	
	
	/**
	 * 
	 * 每5分钟修改支付订单状态
	 * 
	 */
	//@Scheduled(cron = "0 */1 *  * * * ")
	public void updateRechargeRecord() {
		payService.updateWxPay();
	}

}
