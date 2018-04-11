package com.szqj.weborg.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szqj.cms.domain.OutKeyInfo;
import com.szqj.cms.domain.OutKeyInfoRepository;
import com.szqj.cms.domain.OutNewsInfo;
import com.szqj.cms.domain.OutNewsInfoRepository;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

@Service
@Transactional
public class OOSpiderService implements PageProcessor {
	
	
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private OutNewsInfoRepository outNewsInfoRepository;
	
	@Autowired
	private OutKeyInfoRepository outKeyInfoRepository;
	
	private Site site = Site.me().setSleepTime(500).setTimeOut(3 * 60 * 1000)
    .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0")
    .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3").setDomain("news.baidu.com");
	
	@Scheduled(cron = "0 */5 *  * * * ")
    public void startSearch() {
    	Spider spider = Spider.create(this);
    	List<String> urlList=getUrlList();
    	
    	int size=urlList.size();  
        String[] array = (String[])urlList.toArray(new String[size]);  
    	spider.addUrl(array).run();
    	//spider.addUrl("http://news.baidu.com/ns?q1=管道泄漏 燃气爆炸&tn=news&from=news&cl=2&rn=20&ct=1&clk=sortbytime").run();
    	
	}


    private List<String> getUrlList() {
    	List<OutKeyInfo> list = outKeyInfoRepository.findAll(); 
    	List<String> l=new ArrayList<String>();
    	for(OutKeyInfo key:list){
    		String k=key.getKeyStr();
    		String str="http://news.baidu.com/ns?q1="+k+"&tn=news&from=news&cl=0&rn=20&ct=0&clk=sortbytime"+getBaiduSearchStr();
    		l.add(str);
    	}
		return l;
	}


	public void process(Page page) {
    	//System.out.println( page.getHtml());
		 String path=page.getUrl().toString();
		 int s = path.indexOf("q1=")+3;
		 int e = path.indexOf("&tn");
		 String keyword = path.substring(s, e);
		 
    	 Selectable list = page.getHtml().xpath("//h3[@class='c-title']/");
    	 Selectable listdesc = page.getHtml().xpath("//div[@class='c-summary']");
    	
    	for(int i=0;i<list.nodes().size();i++){
    		 Selectable n = list.nodes().get(i);
    		 Selectable ndesc=(Selectable)listdesc.nodes().get(i);
    		 Selectable l=n.links();
    		 
    		String url = l.toString();
    		String title=n.xpath("/a/html()").toString();
    		title=title.replaceAll("<em>", "");
    		title=title.replaceAll("</em>", "");
    		
    		String desc=ndesc.xpath("//[@class='c-author']/html()").toString();
    		System.out.println("href="+url+"title="+title+"desc="+desc);
    		
    		List<OutNewsInfo> news = outNewsInfoRepository.findByUrl(url);
    		if(news!=null&&news.size()==0){
	    		OutNewsInfo outNewsInfo=new OutNewsInfo();
	    		outNewsInfo.setCreateDate(new Date());
	    		outNewsInfo.setDescr(desc);
	    		outNewsInfo.setUrl(url);
	    		outNewsInfo.setTitle(title);
	    		if(title.indexOf("死")>-1||title.indexOf("伤")>-1){
	    			outNewsInfo.setLevel(1);
	    		}else{
	    			outNewsInfo.setLevel(0);
	    		}
	    		
	    		outNewsInfo.setState(0);
	    		outNewsInfo.setKeyword(keyword);
	    		outNewsInfoRepository.save(outNewsInfo);
    		}
    	}
    	
        
    }

    
    public Site getSite() {
        return site;

    }
    
    
    private String getBaiduSearchStr(){
    	long bt=0;
    	long et=0;
    	try {
			 bt = new SimpleDateFormat("yyyy-MM-dd").parse(getCurDay()).getTime()/1000;
			 et = new SimpleDateFormat("yyyy-MM-dd").parse(getCurDay()).getTime()/1000;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "&begin_date="+getCurDay()+"&end_date="+getCurDay()+"&bt="+bt+"&et="+et;
    }
    
    private String getStartDay(){
    	Calendar ca = Calendar.getInstance();
    	int year = ca.get(Calendar.YEAR);
    	int month = ca.get(Calendar.MONTH)+1;
    	return year+"-"+month+"-"+1;
    	
    }
    
    private String getCurDay(){
    	Calendar ca = Calendar.getInstance();
    	int year = ca.get(Calendar.YEAR);
    	int month = ca.get(Calendar.MONTH);
    	int date = ca.get(Calendar.DATE);
    	return year+"-"+month+"-"+date;
    }
    
    
    public static void main(String[] args) {
    	OOSpiderService o=new OOSpiderService();
    	o.startSearch();
    }
    

}
