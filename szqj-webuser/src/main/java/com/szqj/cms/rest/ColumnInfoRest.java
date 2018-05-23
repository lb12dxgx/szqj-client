package com.szqj.cms.rest;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.cms.domain.ColumnInfo;
import com.szqj.cms.domain.ColumnInfoRepository;
import com.szqj.cms.rest.vo.ColumnInfoNode;
import com.szqj.cms.service.ColumnInfoService;
import com.szqj.util.RestJson;



@RestController
@RequestMapping("/system/column")
@EnableAutoConfiguration
public class ColumnInfoRest {
	
	

	@Autowired
	private ColumnInfoRepository columnInfoRepository;
	
	@Autowired
	private ColumnInfoService columnInfoService;
	
	@RequestMapping(value = "/save.do"  )
	public RestJson save(ColumnInfo colunm){
		columnInfoRepository.save(colunm);
		return RestJson.createSucces(colunm);
	}
	
	
	@RequestMapping(value = "/update.do"  )
	public RestJson update(ColumnInfo colunm){
		columnInfoRepository.save(colunm);
		return RestJson.createSucces(colunm);
	}
	
	@RequestMapping(value = "/get.do"  )
	public RestJson get(String  columnId){
		ColumnInfo colunm = columnInfoRepository.findById(columnId).get();
		return RestJson.createSucces(colunm);
	}
	
	
	@RequestMapping(value = "/delete.do"  )
	public RestJson delete(String  columnId){
		columnInfoRepository.deleteById(columnId);
		return RestJson.createSucces();
	}
	 
	
	@RequestMapping(value = "/list.do"  )
	public RestJson list(){
		 List<ColumnInfo> list = columnInfoRepository.findAll();
		 return RestJson.createSucces(list);
	}
	
	
	@RequestMapping(value = "/tree.do"  )
	public RestJson tree(){
		List<ColumnInfoNode> list = columnInfoService.getColumnTree(); 
		return RestJson.createSucces(list);
	}
	
	
	@RequestMapping(value = "/treeByPrivage.do"  )
	public RestJson treeByPrivage(String[] columnIdList){
		List<ColumnInfoNode> list = columnInfoService.getTreeByPrivage(columnIdList); 
		return RestJson.createSucces(list);
	}
	
}
