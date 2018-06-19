package com.szqj.website.emp.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.ProductRepository;
import com.szqj.util.RestJson;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.domain.Dict;
import com.szqj.weborg.domain.DictRepository;

@RestController
@RequestMapping("/")
@EnableAutoConfiguration
public class DictControle {
	
	@Autowired
	private DictRepository dictRepository;
	/**
	 * 企业信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/dict/getList.html"  )
	public RestJson info(String dictValue){
		List<Dict> dicts = dictRepository.findByDictValue(dictValue);
		if(dicts!=null&&dicts.size()>0){
			List<Dict> l = dictRepository.findByParentId(dicts.get(0).getDictId());
			return RestJson.createSucces(l); 
		}
		return RestJson.createError();
	}
	

}
