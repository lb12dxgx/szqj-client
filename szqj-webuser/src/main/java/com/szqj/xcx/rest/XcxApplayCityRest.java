package com.szqj.xcx.rest;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.szqj.before.domain.ApplyCity;
import com.szqj.before.domain.ApplyCityRepository;
import com.szqj.before.domain.ApplyOrg;
import com.szqj.before.domain.ApplyOrgRepository;
import com.szqj.service.domain.AccidentInfo;
import com.szqj.service.domain.AccidentInfoRepository;
import com.szqj.service.domain.MyPersonRepository;
import com.szqj.service.domain.Person;
import com.szqj.util.ConstantUtils;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.domain.AccountRepository;
import com.szqj.weborg.domain.FileInfo;
import com.szqj.weborg.domain.FileInfoRepository;

@RestController
@RequestMapping("/xcx/login/")
@EnableAutoConfiguration
public class XcxApplayCityRest {
	
	@Autowired
	private ApplyCityRepository applyCityRepository;
	
	
	
	/**
	 * 
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/before/applaycity/list.xcx"  )
	public RestJson list(){
		List<ApplyCity> list = applyCityRepository.findByState(1);
		return RestJson.createSucces(list);
	}
	
	
	
	
	@RequestMapping(value = "/before/applaycity/save.xcx"  )
	public RestJson save(@ModelAttribute("openid") String openid,ApplyCity applyCity){
		applyCity.setCreateDate(new Date());
		applyCityRepository.save(applyCity);
		return RestJson.createSucces(applyCity);
		
	}
	
	
	@RequestMapping(value = "/before/applaycity/exit.xcx"  )
	public RestJson isExit(String cityName){
		List<ApplyCity> l = applyCityRepository.findByCityName(cityName);
		if(l==null||l.size()==0) {
			return RestJson.createSucces(false);
		}
		return RestJson.createSucces(true);
	}
	
	@RequestMapping(value = "/before/applaycity/get.xcx"  )
	public RestJson get(String cityName){
		List<ApplyCity> l = applyCityRepository.findByCityName(cityName);
		return RestJson.createSucces(l.get(0));
	}

}
