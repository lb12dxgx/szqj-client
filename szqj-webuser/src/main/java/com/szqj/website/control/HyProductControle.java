package com.szqj.website.control;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
		
		return "/hyproduct/index"; 
	}
	
	/**
	 *  招标信息
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/hyproduct/productinfolist.html"  )
	public String productinfolist(ModelMap modelMap,String productTypeCodeOne,String productTypeCodeTwo,String area){
		PageRequest pageable=Tools.getPage(0, 8);
		Page<Product> page=null;
		if(StringUtils.isNotBlank(productTypeCodeTwo)){
			List<Dict> dicts = dictRepository.findByParentId(productTypeCodeTwo);
			List<String> dictList=new ArrayList<String>();
			for(Dict dict:dicts){
				dictList.add(dict.getDictValue());
			}
			page=productRepository.findPageByProductTypeCodeOne(dictList, pageable);
			
		}else if(StringUtils.isNotBlank(productTypeCodeOne)){
			page=productRepository.findPageByProductTypeCodeTwo(productTypeCodeTwo, pageable);
		}else{
			page=productRepository.findPage(pageable);
		}
	
		modelMap.put("page", page);
		return "/hyproduct/zbinfolist"; 
	}
	
	
	/**
	 *  招标信息查看
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/hyproduct/productview.html"  )
	public String productview(ModelMap modelMap,String productId){
		Product  product = productRepository.findById(productId).get();
		modelMap.put("product", product);
		return "/hyproduct/productview"; 
	}
	
	
	
	/**
	 *  招标信息
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/hyproduct/zbinfolist.html"  )
	public String zbinfolist(ModelMap modelMap,String zbXmName,String area){
		PageRequest pageable=Tools.getPage(0, 8);
		Page<ZbInfo> page=null;
		if(StringUtils.isNotBlank(zbXmName)&&StringUtils.isNotBlank(area)){
			page=zbInfoRepository.findPageByZbXmNameAndArea(area, zbXmName, pageable);
		}else if(StringUtils.isBlank(zbXmName)&&StringUtils.isBlank(area)){
			page=zbInfoRepository.findPage(pageable);
		}else if(StringUtils.isNotBlank(zbXmName)){
			page=zbInfoRepository.findPageByZbXmName(zbXmName, pageable);
		}else if(StringUtils.isNotBlank(area)){
			page=zbInfoRepository.findPageByArea(area, pageable);
		}
		modelMap.put("page", page);
		return "/hyproduct/zbinfolist"; 
	}
	
	
	/**
	 *  招标信息查看
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/hyproduct/zbinfoview.html"  )
	public String zbinfoview(ModelMap modelMap,String zbInfoId){
		ZbInfo zbInfo = zbInfoRepository.findById(zbInfoId).get();
		modelMap.put("zbInfo", zbInfo);
		return "/hyproduct/zbinfoview"; 
	}
	
	
	/**
	 *  采购信息
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/hyproduct/buyinfolist.html"  )
	public String buyinfolist(ModelMap modelMap,String buyInfoName){
		PageRequest pageable=Tools.getPage(0, 8);
		Page<BuyInfo> page=null;
		if(StringUtils.isNotBlank(buyInfoName)){
			page=buyInfoRepository.findPageByBuyInfoName(buyInfoName, pageable);
		}else {
			page=buyInfoRepository.findPage(pageable);
		}
		modelMap.put("page", page);
		return "/hyproduct/buyinfolist"; 
	}
	
	
	/**
	 *  采购信息查看
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/hyproduct/buyinfoview.html"  )
	public String buyinfoview(ModelMap modelMap,String buyInfoId){
		BuyInfo buyInfo = buyInfoRepository.findById(buyInfoId).get();
		modelMap.put("buyInfo", buyInfo);
		return "/hyproduct/zbinfoview"; 
	}
	
	
	/**
	 *  采购信息查看
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/hyproduct/enterpriseview.html"  )
	public String enterpriseview(ModelMap modelMap,String buyInfoId){
		BuyInfo buyInfo = buyInfoRepository.findById(buyInfoId).get();
		modelMap.put("buyInfo", buyInfo);
		return "/hyproduct/zbinfoview"; 
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
		
		for(BuyInfo buyInfo:page.getContent()) {
			
			String enterpriseId = buyInfo.getEnterpriseId();
			String empName = enterpriseRepository.findById(enterpriseId).get().getEnterpriseName();
			buyInfo.setEmpName(empName);
			
		}
		
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
			String enterpriseId = product.getEnterpriseId();
			String empName = enterpriseRepository.findById(enterpriseId).get().getEnterpriseName();
			product.setEmpName(empName);
			
		}
		
		modelMap.put("productList",list);
		
	}

}
