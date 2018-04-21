package com.szqj.cms.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.cms.domain.OutNewsInfo;
import com.szqj.cms.domain.OutNewsInfoRepository;
import com.szqj.cms.rest.vo.OutNewsVo;
import com.szqj.util.RestJson;



@RestController
@RequestMapping("/system/outnews/")
@EnableAutoConfiguration
public class OutNewsRest {
	


	@Autowired
	private  OutNewsInfoRepository  outNewsInfoRepository;
	
	
	
	//根据栏目返回内容列表
	@RequestMapping(value = "listByKeWord.do"  )
	public RestJson listByKeWord( String keyword, Integer state){
		List<OutNewsInfo> list = outNewsInfoRepository.findByKeywordAndState(keyword, state);
		return RestJson.createSucces(list);
	}
	
	//根据栏目返回内容列表
	@RequestMapping(value = "list.do"  )
	public RestJson list(){
		List<Object[]> l = outNewsInfoRepository.findNumByKey();
		List<OutNewsInfo> list = outNewsInfoRepository.findByState();
		
		List<OutNewsVo> listVo = new ArrayList<OutNewsVo>();
		Map<String,OutNewsVo> mapVo=new HashMap<String,OutNewsVo >();
		
		HashMap<String,String> map=new HashMap<String,String>();
		for(Object[] obArray:l){
			map.put(String.valueOf(obArray[0]), String.valueOf(obArray[1]));
		}
		
		for(OutNewsInfo outNews:list){
			String key = outNews.getKeyword();
			String num=map.get(key);
			if(Integer.parseInt(num)>0){
				if(mapVo.get(key)==null){
					OutNewsVo vo=new OutNewsVo();
					vo.setKeyword(key);
					vo.setNum(num);
					vo.getOutNewsInfos().add(outNews);
					mapVo.put(key, vo);
					listVo.add(vo);
				}else{
					mapVo.get(key).getOutNewsInfos().add(outNews);
				}
			}
			}
		
		return RestJson.createSucces(listVo);
	}
	
	
	//保存账号信息
	@RequestMapping(value = "view.do"  )
	public RestJson save(String outnewsinfoId){
		OutNewsInfo outNewsInfo = outNewsInfoRepository.findById(outnewsinfoId).get();
		outNewsInfo.setState(1);
		outNewsInfoRepository.save(outNewsInfo);
		return RestJson.createSucces(outNewsInfo);
	}
	
	
	
	//删除
	@RequestMapping(value = "delete.do"  )
	public RestJson delete( String outnewsinfoId){
		OutNewsInfo outNewsInfo = outNewsInfoRepository.findById(outnewsinfoId).get();
		outNewsInfo.setState(3);
		outNewsInfoRepository.save(outNewsInfo);
		return RestJson.createSucces();
	}
	
	
	//删除
		@RequestMapping(value = "alldelete.do"  )
		public RestJson allDelete(String keyWord){
			outNewsInfoRepository.deleteByKeyWord(keyWord);
			return RestJson.createSucces();
		}
	
	
	
	
		
	
	
	

}
