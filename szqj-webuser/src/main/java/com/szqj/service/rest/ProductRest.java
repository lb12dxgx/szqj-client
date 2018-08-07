package com.szqj.service.rest;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.service.domain.JobInfo;
import com.szqj.service.domain.Product;
import com.szqj.service.domain.ProductRepository;
import com.szqj.service.domain.ZbInfo;
import com.szqj.train.domain.TrainClass;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;



@RestController
@RequestMapping("/system/product/")
@EnableAutoConfiguration
public class  ProductRest {
	
	@Autowired
	private ProductRepository productRepository;
	
	

	@RequestMapping(value = "list.do"  )
	public RestJson list( String enterpriseId){
		List<Product> list = productRepository.findByEnterpriseId(enterpriseId);
		return RestJson.createSucces(list);
		
	}
	
	
	
	@RequestMapping(value = "save.do"  )
	public RestJson save( Product product){
		product.setCreateDate(new Date());
		productRepository.save(product);
		return RestJson.createSucces(product);
	}
	
	@RequestMapping(value = "get.do"  )
	public RestJson get(String productId){
		Product product = productRepository.findById(productId).get();
		return RestJson.createSucces(product);
	}
	
	
	@RequestMapping(value = "update.do"  )
	public RestJson update( Product product){
		productRepository.save(product);
		return RestJson.createSucces(product);
	}
	
	
	@RequestMapping(value = "delete.do"  )
	public RestJson delete( String productId){
		productRepository.deleteById(productId);
		return RestJson.createSucces();
	}
	
	
	@RequestMapping(value = "changeLevel.do"  )
	public RestJson changeLevel(String productId){
		Product product = productRepository.findById(productId).get();
		if(product.getLevel()==10) {
			product.setLevel(30);
		}else {
			product.setLevel(10);
		}
	
		productRepository.save(product);
		return RestJson.createSucces();
	}
	
	
	
		
	
	
	

}
