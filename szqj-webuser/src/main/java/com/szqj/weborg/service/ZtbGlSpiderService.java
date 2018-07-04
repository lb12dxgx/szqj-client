package com.szqj.weborg.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szqj.service.domain.ZbGlInfo;
import com.szqj.service.domain.ZbGlInfoRepository;
import com.szqj.service.domain.ZbInfo;
import com.szqj.service.domain.ZbInfoRepository;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

@Service
@Transactional
public class ZtbGlSpiderService implements PageProcessor {
	
	@Autowired 
	private ZbGlInfoRepository zbGlInfoRepository;
	

	private Site site = Site.me().setSleepTime(500).setTimeOut(3 * 60 * 1000)
    .setUserAgent("Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; fr) Presto/2.9.168 Version/11.52")
    .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
	
	@Scheduled(cron = "0 0 */3  * * * ") 
    public void startSearch() {  
    	Spider spider = Spider.create(this);
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	String searchDate = formatter.format(new Date());
    	String url="https://r.newssdk.com/bid/item/search?include_keywords=公路&type=1&searchtype=0&page=1&region=内蒙&searchscope=title&size=100";
		spider.addUrl(url).run();
    	
    }

   
    public void process(Page page) {
    	System.out.println( "抓取招标数据");
		Json json = page.getJson();
		ZtbPage ztb = json.toObject(ZtbPage.class);
		List<Ztb> l = ztb.getLi();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for(Ztb zb:l){
			
			List<ZbGlInfo> list = zbGlInfoRepository.findListByUrl(zb.getUrl());
			
			if(list==null||list.size()==0){
				ZbGlInfo zbInfo=new ZbGlInfo();
				zbInfo.setArea(zb.getArea());
				Date date=null;
				try {
					date = formatter.parse(zb.getPublish_time());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				zbInfo.setPublishDate(date);
				zbInfo.setCreateDate(new Date());
				zbInfo.setZbXmName(zb.getTitle());
				zbInfo.setUrl(zb.getUrl());
			
				
				ZtbGlContentProcessor ztbProcessor=new ZtbGlContentProcessor(zbInfo); 
				Spider spider = Spider.create(ztbProcessor);
				String url=zb.getUrl().replaceAll("item/default2.html", "hnews/item");
				spider.addUrl(url).run();
				System.out.println("结束内容");
				zbGlInfoRepository.save(zbInfo);
			}
			
		}
    	
    }

    
    public Site getSite() {
        return site;

    }
    
 
    
    public static void main(String[] args) {
    	/*ZtbSpiderService o=new ZtbSpiderService();
    	o.startSearch();*/
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	try {
    		formatter.parse("2018-06-07");
			//formatter.parse("2018-06-07");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

}
