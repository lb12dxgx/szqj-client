package com.szqj.website.control;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.szqj.before.domain.ApplyOrg;
import com.szqj.before.domain.ApplyOrgRepository;
import com.szqj.cms.domain.ColumnInfo;
import com.szqj.cms.domain.ColumnInfoRepository;
import com.szqj.cms.domain.ContentInfo;
import com.szqj.cms.domain.ContentInfoRepository;
import com.szqj.util.Tools;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class KnowledgeControle {
	
	@Autowired
	private ColumnInfoRepository columnInfoRepository;
	@Autowired
	private ContentInfoRepository contentInfoRepository;
	
	
	/**
	 * 电子资料
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/knowledge/index.html")
	public String index_news(String columnCode,Integer pageNum, Integer size, ModelMap modelMap){
		
		if(StringUtils.isBlank(columnCode)) {
			columnCode="1_hy_zs_zc";
		}
		
		ColumnInfo columnInfo=columnInfoRepository.findByColumnCode(columnCode);
		PageRequest pageable=Tools.getPage(pageNum, 5);
		Page<ContentInfo> page=contentInfoRepository.findByColumnId(columnInfo.getColumnId(), pageable);
		List<ContentInfo> list=contentInfoRepository.findListByColumnId(columnInfo.getColumnId());
		modelMap.put("page", page);
		modelMap.put("columnCode", columnCode);
		
		return "knowledge/index"; 
	}


	/**
	 * 知识库详情
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/knowledge/detail.html"  )
	public String detail_news(String contentId, ModelMap modelMap){
		ContentInfo content = contentInfoRepository.findById(contentId).get();
		modelMap.put("content", content);
		return "knowledge/detail"; 
	}
	
	

	

	

}
