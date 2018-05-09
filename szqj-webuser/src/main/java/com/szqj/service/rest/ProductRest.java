package com.szqj.service.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.cms.domain.ColumnInfoRepository;
import com.szqj.service.domain.Product;
import com.szqj.service.domain.ProductRepository;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.service.AccountService;



@RestController
@RequestMapping("/system/product/")
@EnableAutoConfiguration
public class  ProductRest {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	//根据账号类型返回账号列表
	@RequestMapping(value = "list.do"  )
	
	public RestJson list( String productName,   Integer pageNum, Integer size){
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		Page<Product> page = productRepository.findPageByProductName(productName, pageable);
		return RestJson.createSucces(page);
	}
	
	
	//保存账号信息
	@RequestMapping(value = "save.do"  )
	public RestJson save( Product product){
		product.setCreateDate(new Date());
		productRepository.save(product);
		return RestJson.createSucces(product);
	}
	
	//更新
	@RequestMapping(value = "update.do"  )
	public RestJson update( Product product){
		productRepository.save(product);
		return RestJson.createSucces(product);
	}
	
	//删除
	@RequestMapping(value = "delete.do"  )
	public RestJson delete( String productId){
		productRepository.deleteById(productId);
		return RestJson.createSucces();
	}
	
	
		
	
	
	

}
