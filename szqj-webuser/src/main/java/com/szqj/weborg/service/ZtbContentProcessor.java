package com.szqj.weborg.service;

import com.szqj.service.domain.ZbInfo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class ZtbContentProcessor implements PageProcessor {

	private ZbInfo zbInfo;
	
	private Site site = Site.me().setSleepTime(500).setTimeOut(3 * 60 * 1000)
		    .setUserAgent("Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; fr) Presto/2.9.168 Version/11.52")
		    .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
		    .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
	
	public ZtbContentProcessor(ZbInfo zbInfo) {
		this.zbInfo=zbInfo;
	}

	@Override
	public Site getSite() {
		 return site;
	}

	@Override
	public void process(Page page) {
    	System.out.println( "抓取招标具体内容");
    	String content=page.getJson().jsonPath("$.content").get();
    	String url=page.getJson().jsonPath("$.url").get();
    	String fmedia=page.getJson().jsonPath("$.fmedia").get();
    	System.out.println(page.getJson());
    	zbInfo.setZbContent(content);
    	zbInfo.setSourceUrl(url);
    	zbInfo.setFmedia(fmedia);
		
	}

}
