package com.szqj.website.emp.control;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.EnterpriseRepository;
import com.szqj.service.domain.Product;
import com.szqj.service.domain.ProductRepository;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.domain.Dict;
import com.szqj.weborg.domain.DictRepository;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class ProductControle {
	
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	
	@Autowired
	private DictRepository dictRepository;
	
	
	/**
	 * 产品信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/emp/productinfo.html"  )
	public String productinfo(@SessionAttribute Account account,  ModelMap modelMap){
		Enterprise enterprise = enterpriseRepository.findByAccountId(account.getAccountId()).get(0);
		List<Product> list=productRepository.findByEnterpriseId(enterprise.getEnterpriseId());
		modelMap.put("list", list);
		return "emp/productinfo"; 
	}
	
	
	
	@RequestMapping(value = "/emp/productinfo/add.html"  )
	public String productinfoAdd(@SessionAttribute Account account, ModelMap modelMap){
		Enterprise enterprise = enterpriseRepository.findByAccountId(account.getAccountId()).get(0);
		modelMap.put("productPicId", UUID.randomUUID().toString());
		modelMap.put("enterpriseId", enterprise.getEnterpriseId());
		return "emp/productinfo_add"; 
	}
	
	
	@RequestMapping(value = "/emp/productinfo/save.do"  )
	public String productinfoSave(Product  product,  ModelMap modelMap){
		product.setLevel(10);
		product.setCreateDate(new Date());
		List<Dict> dicts = dictRepository.findByDictValue(product.getProductTypeCode());
		product.setProductType(dicts.get(0).getDictName());
		productRepository.save(product);
		
		return "redirect:/emp/productinfo.html"; 
		
	}
	
	@RequestMapping(value = "/emp/productinfo/edit.html"  )
	public String productinfoEdit(String productId,  ModelMap modelMap){
		Product product = productRepository.findById(productId).get();
		List<Dict> dicts = dictRepository.findByDictValue(product.getProductTypeCode());
		Dict pdict = dictRepository.findById(dicts.get(0).getPdictId()).get();
		modelMap.put("product", product);
		modelMap.put("ptypeCode", pdict.getDictValue());
		return "emp/productinfo_edit"; 
	}
	
	
	@RequestMapping(value = "/emp/productinfo/update.do"  )
	public String productinfoUpdate(Product  product,  ModelMap modelMap){
		Product productRet = productRepository.findById(product.getProductId()).get();
		Tools.copyBeanForUpdate(product, productRet);
		productRepository.save(productRet);
		return "redirect:/emp/productinfo.html"; 
		
	}
	
	@RequestMapping(value = "/emp/productinfo/del.do"  )
	public String productinfoDel(String  productId,  ModelMap modelMap){
		productRepository.deleteById(productId);
		return "redirect:/emp/productinfo.html"; 
		
	}

}
