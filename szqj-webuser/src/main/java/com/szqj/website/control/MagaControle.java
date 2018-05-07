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
public class MagaControle {
	
	@Autowired
	private ColumnInfoRepository columnInfoRepository;
	@Autowired
	private ContentInfoRepository contentInfoRepository;
	
	
	/**
	 * 电子资料
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/maga/index.html")
	public String index_news(String columnCode,Integer pageNum, Integer size, ModelMap modelMap){
		
		if(StringUtils.isBlank(columnCode)) {
			columnCode="1_dz_zl_qk";
		}
		
		ColumnInfo columnInfo=columnInfoRepository.findByColumnCode(columnCode);
		List<ContentInfo> list=contentInfoRepository.findListByColumnId(columnInfo.getColumnId());
		modelMap.put("list", list);
		modelMap.put("columnCode", columnCode);
		
		return "maga/index"; 
	}


	/**
	 * 电子资料详情
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/maga/detail.html"  )
	public String detail_news(String contentId, ModelMap modelMap){
		ContentInfo content = contentInfoRepository.findById(contentId).get();
		modelMap.put("content", content);
		return "maga/detail"; 
	}
	
	

	

	

}
