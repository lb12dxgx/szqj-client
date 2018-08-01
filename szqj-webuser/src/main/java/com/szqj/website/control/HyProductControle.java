package com.szqj.website.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.szqj.weborg.domain.Dict;
import com.szqj.weborg.domain.DictRepository;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class HyProductControle {
	
	@Autowired
	private DictRepository dictRepository;
	/**
	 * 行业采购
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/hyproduct/index.html"  )
	public String index(ModelMap modelMap){
		
		producTypeList(modelMap);
		zbInfoList(modelMap);
		buyInfoList(modelMap);
		topEnterpriseList(modelMap);
		topProductList(modelMap);
		
		return "hyproduct/index"; 
	}
	
	/**
	 * 设置产品分类
	 * @param modelMap
	 */
	private void producTypeList(ModelMap modelMap) {
		List<Dict> pList = dictRepository.findByDictValue("cp_type");
		Dict parentDict=pList.get(0);
		List<Dict> dictList = dictRepository.findByParentId(parentDict.getDictId());
		for(Dict element:dictList) {
			List<Dict> l = dictRepository.findByParentId(element.getDictId());
			element.getChildren().addAll(l);
		}
		 modelMap.put("dictList",dictList);
	}
	
	/**
	 * 设置招标信息
	 * @param modelMap
	 */
	private void zbInfoList(ModelMap modelMap) {
		
		asda
	}
	
	
	/**
	 * 设置采购信息
	 * @param modelMap
	 */
	private void buyInfoList(ModelMap modelMap) {
		
		
	}
	
	
	/**
	 * 设置知名企业
	 * @param modelMap
	 */
	private void topEnterpriseList(ModelMap modelMap) {
		
		
	}
	
	
	/**
	 * 设置知名产品
	 * @param modelMap
	 */
	private void topProductList(ModelMap modelMap) {
		
		
	}

}
