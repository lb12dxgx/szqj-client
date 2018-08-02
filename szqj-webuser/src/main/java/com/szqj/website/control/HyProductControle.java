package com.szqj.website.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.szqj.service.domain.BuyInfo;
import com.szqj.service.domain.BuyInfoRepository;
import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.EnterpriseRepository;
import com.szqj.service.domain.Product;
import com.szqj.service.domain.ProductRepository;
import com.szqj.service.domain.ZbInfo;
import com.szqj.service.domain.ZbInfoRepository;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Dict;
import com.szqj.weborg.domain.DictRepository;
import com.szqj.weborg.domain.FileInfo;
import com.szqj.weborg.domain.FileInfoRepository;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class HyProductControle {
	
	@Autowired
	private DictRepository dictRepository;
	@Autowired
	private ZbInfoRepository zbInfoRepository;
	@Autowired
	private BuyInfoRepository buyInfoRepository;
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private FileInfoRepository fileInfoRepository;
	
	
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
		PageRequest pageable=Tools.getPage(0, 5);
		Page<ZbInfo> page=zbInfoRepository.findPage(pageable);
		modelMap.put("zbList",page.getContent());
		
	}
	
	
	/**
	 * 设置采购信息
	 * @param modelMap
	 */
	private void buyInfoList(ModelMap modelMap) {
		PageRequest pageable=Tools.getPage(0, 5);
		Page<BuyInfo> page = buyInfoRepository.findPage(pageable);
		modelMap.put("buyList",page.getContent());
		
	}
	
	
	/**
	 * 设置知名企业
	 * @param modelMap
	 */
	private void topEnterpriseList(ModelMap modelMap) {
		List<Enterprise> list = enterpriseRepository.findVipList();
		
		for(Enterprise enterprise:list) {
			String tFiled = enterprise.getEnterprisePicId();
			List<FileInfo> files = fileInfoRepository.findByBussinessId(tFiled);
			if(files!=null&&files.size()>0) {
				enterprise.setEnterprisePicPath(files.get(0).getFileWebPath());
			}
		}
		
		modelMap.put("entList",list);
		
	}
	
	
	/**
	 * 设置知名产品
	 * @param modelMap
	 */
	private void topProductList(ModelMap modelMap) {
		List<Product> list = productRepository.findVipList();
		
		for(Product product:list) {
			String tFiled = product.getProductPicId();
			List<FileInfo> files = fileInfoRepository.findByBussinessId(tFiled);
			if(files!=null&&files.size()>0) {
				product.setProductPicPath(files.get(0).getFileWebPath());
			}
		}
		
		modelMap.put("productList",list);
		
	}

}
