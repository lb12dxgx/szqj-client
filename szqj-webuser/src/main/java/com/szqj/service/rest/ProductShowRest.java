package com.szqj.service.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.service.domain.ProductShow;
import com.szqj.service.domain.ProductShowRepository;
import com.szqj.util.RestJson;



@RestController
@RequestMapping("/system/productshow/")
@EnableAutoConfiguration
public class  ProductShowRest {
	
	@Autowired
	private ProductShowRepository productShowRepository;
	
	

	@RequestMapping(value = "list.do"  )
	public RestJson list(){
		List<ProductShow> list = productShowRepository.findList();
		return RestJson.createSucces(list);
		
	}
	
	
	
	@RequestMapping(value = "save.do"  )
	public RestJson save( ProductShow productShow){
		productShow.setCreateDate(new Date());
		productShowRepository.save(productShow);
		return RestJson.createSucces(productShow);
	}
	
	@RequestMapping(value = "get.do"  )
	public RestJson get(String productShowId){
		ProductShow productShow = productShowRepository.findById(productShowId).get();
		return RestJson.createSucces(productShow);
	}
	
	
	@RequestMapping(value = "update.do"  )
	public RestJson update( ProductShow productShow){
		productShowRepository.save(productShow);
		return RestJson.createSucces(productShow);
	}
	
	
	@RequestMapping(value = "delete.do"  )
	public RestJson delete( String productShowId){
		productShowRepository.deleteById(productShowId);
		return RestJson.createSucces();
	}
	
	
	
	
		
	
	
	

}
