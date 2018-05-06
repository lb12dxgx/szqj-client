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
public class AccidentControle {
	
	@Autowired
	private ColumnInfoRepository columnInfoRepository;
	@Autowired
	private ContentInfoRepository contentInfoRepository;
	
	
	
	/**
	 * 管线安全
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/accident/index.html")
	public String index_news(String columnCode,Integer pageNum, Integer size, ModelMap modelMap){
		
		if(StringUtils.isBlank(columnCode)) {
			columnCode="1_gx_sg_info";
		}
		
		ColumnInfo columnInfo=columnInfoRepository.findByColumnCode(columnCode);
		PageRequest pageable=Tools.getPage(pageNum, 5);
		Page<ContentInfo> page=contentInfoRepository.findByColumnId(columnInfo.getColumnId(), pageable);
		
		
		modelMap.put("page", page);
		modelMap.put("columnCode", columnCode);
		
		setRight(modelMap);
		
		return "accident/index"; 
	}


	private void setRight(ModelMap modelMap) {
		ColumnInfo columnInfo=columnInfoRepository.findByColumnCode("2_gx_sg_fa");
		List<ContentInfo> rList = contentInfoRepository.findListByColumnId(columnInfo.getColumnId());
		modelMap.put("rList", rList);
	}
	
	
	/**
	 * 产业资讯详情
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/accident/detail.html"  )
	public String detail_news(String contentId, ModelMap modelMap){
		ColumnInfo pcolumnInfo=columnInfoRepository.findByColumnCode("1_hy_xw");
		setRight(modelMap);
		ContentInfo content = contentInfoRepository.findById(contentId).get();
		modelMap.put("content", content);
		return "accident/detail"; 
	}
	
	

	

	

}
