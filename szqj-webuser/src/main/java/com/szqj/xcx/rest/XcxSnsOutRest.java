package com.szqj.xcx.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.mail.domain.Gift;
import com.szqj.mail.domain.GiftRepository;
import com.szqj.service.domain.Meet;
import com.szqj.service.domain.MeetRepository;
import com.szqj.sns.domain.Problem;
import com.szqj.sns.service.SnsService;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;

@RestController
@RequestMapping("/xcx/out")
@EnableAutoConfiguration
public class XcxSnsOutRest {
	
	@Autowired
	private  SnsService  snsService;
	
	@Autowired
	private MeetRepository meetRepository;
	
	@Autowired
	private GiftRepository giftRepository;
	
	@RequestMapping(value = "/sns/getProblemByShareCode.xcx"  )
	public RestJson getProblemByShareCode(String shareCode){
		Problem problem = snsService.getProblemByShareCode(shareCode,null);
		return RestJson.createSucces(problem); 
	}
	
	
	@RequestMapping(value = "/mail/giftList.xcx"  )
	public RestJson giftList(){
		List<Gift> list = giftRepository.findSellGift();
		return RestJson.createSucces(list);
	}
	
	
	@RequestMapping(value = "/meet/list.xcx"  )
	public RestJson list(Integer pageNum, Integer size){
		PageRequest pageable=Tools.getPage(pageNum,5);
		Page<Meet> page=null;
		page=meetRepository.findPage(pageable);
		return RestJson.createSucces(page);
	}

}
