package com.szqj.website.control;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.szqj.cms.domain.ContentInfo;
import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.Product;
import com.szqj.service.domain.ZbInfo;
import com.szqj.train.domain.TrainPlan;
import com.szqj.weborg.service.IndexService;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class IndexControle {
	
	
	@Autowired
	private IndexService indexService;
	

	
	/**
	 * 首页
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/index.do"  )
	public String index(ModelMap modelMap){
		
		HashMap map=new HashMap();
		List<ContentInfo> banerNewList =indexService.getBanerNews(map);
		List<ContentInfo> ywNewList =indexService.getYwNews(map);
		List<ContentInfo> qyNewList =indexService.getQyNews();
		List<ContentInfo> gjNewList = indexService.getGjNews();
		List<ContentInfo> yqNewList =indexService.getYqNews();
		
		modelMap.put("banerNewList", banerNewList);
		modelMap.put("ywNewList", ywNewList);
		modelMap.put("qyNewList", qyNewList);
		modelMap.put("gjNewList", gjNewList);
		modelMap.put("yqNewList", yqNewList);
		
		List<ContentInfo> spNewList = indexService.getSpNews(map);
		modelMap.put("spNewList", spNewList);
		
		Integer zcNum = indexService.getNumByZc();
		Integer flNum = indexService.getNumByFl();
		Integer bjNum = indexService.getNumByBj();
		Integer lwNum = indexService.getNumByLw();
		
		modelMap.put("zcNum", zcNum);
		modelMap.put("flNum", flNum);
		modelMap.put("bjNum", bjNum);
		modelMap.put("lwNum", lwNum);
		
		List<Product> productList = indexService.getProductList(map);
		List<Enterprise> enterpriseList = indexService.getEnterpriseList(map);
		modelMap.put("productList", productList);
		modelMap.put("enterpriseList", enterpriseList);
		
		
		List<ZbInfo> zbInfoList = indexService.getZbInfoList();
		List<TrainPlan> trainPlanList = indexService.getTrainPlanList();
		modelMap.put("zbInfoList", zbInfoList);
		modelMap.put("trainPlanList", trainPlanList);
		
		List<ContentInfo> qkNewList = indexService.getQkNews(map);
		modelMap.put("qkNewList", qkNewList);
		modelMap.put("fileMap", map);
		
		return "index_temp";
	}
	
	
	
	

	
	
	
	
	
	

}
