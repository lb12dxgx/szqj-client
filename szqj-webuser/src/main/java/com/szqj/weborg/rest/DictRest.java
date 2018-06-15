package com.szqj.weborg.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.util.RestJson;
import com.szqj.weborg.domain.Dict;
import com.szqj.weborg.domain.DictRepository;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/system/dict")
@EnableAutoConfiguration
public class DictRest {
	
	

	@Autowired
	private DictRepository dictRepository;
	
	
	
	@RequestMapping(value = "/save.do"  )
	public RestJson save(Dict dict){
		dictRepository.save(dict);
		return RestJson.createSucces(dict);
	}
	
	
	@RequestMapping(value = "/update.do"  )
	public RestJson update(Dict dict){
		dictRepository.save(dict);
		return RestJson.createSucces(dict);
	}
	
	@RequestMapping(value = "/get.do"  )
	public RestJson get(String  dictId){
		Dict dict = dictRepository.findById(dictId).get();
		return RestJson.createSucces(dict);
	}
	
	
	@RequestMapping(value = "/delete.do"  )
	public RestJson delete(String  dictId){
	     dictRepository.deleteById(dictId);
		return RestJson.createSucces(dictId);
	}
	 
	
	@RequestMapping(value = "/list.do"  )
	public RestJson list(String  pdictId){
		 List<Dict> list = dictRepository.findByParentId(pdictId);
		 return RestJson.createSucces(list);
	}
	
	
	@RequestMapping(value = "/tree.do"  )
	public RestJson tree(){
		Dict root=dictRepository.getRoot();
		if(root==null){
			root=new Dict();
			root.setDictName("系统字典");
			root.setDictValue("-1");
			root.setPdictId("-1");
			dictRepository.save(root);
		}
		addTreeNode(root);
		List<Dict> list =new ArrayList<Dict>();
		list.add(root);
		return RestJson.createSucces(list);
	}
	
	
	private void addTreeNode(Dict dict){
		List<Dict> children = dictRepository.findByParentId(dict.getDictId());
		for(Dict par:children){
			addTreeNode(par);
		}
		if(children!=null&&children.size()>0){
			dict.setChildren(children);
		}
	}



}
