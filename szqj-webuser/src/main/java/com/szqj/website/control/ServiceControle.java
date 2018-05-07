package com.szqj.website.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.szqj.cms.domain.ColumnInfo;
import com.szqj.cms.domain.ColumnInfoRepository;
import com.szqj.cms.domain.ContentInfo;
import com.szqj.cms.domain.ContentInfoRepository;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class ServiceControle {
	

	@Autowired
	private ColumnInfoRepository columnInfoRepository;
	@Autowired
	private ContentInfoRepository contentInfoRepository;
	
	
	/**
	 * 电子证书认证
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/service/reg.html"  )
	public String reg(ModelMap modelMap){
		return "service/reg";
	}
	
	/**
	 * 电子证书认证
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/service/regResult.html"  )
	public String regResult(ModelMap modelMap){
		return "service/regResult";
	}
	
	
	/**
	 * 电子证书查询
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/service/cardSearch.html"  )
	public String cardSearch(ModelMap modelMap){
		return "service/cardSearch"; 
	}
	
	
	/**
	 * 电子证书查询结果
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/service/cardResult.html"  )
	public String cardResult(ModelMap modelMap){
		return "service/cardResult"; 
	}
	
	
	/**
	 * 会议展览
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/service/meet.html"  )
	public String index_meet(ModelMap modelMap){
		String columnCode="1_cy_fw_hy";
		ColumnInfo columnInfo=columnInfoRepository.findByColumnCode(columnCode);
		List<ContentInfo> list=contentInfoRepository.findListByColumnId(columnInfo.getColumnId());
		modelMap.put("list", list);
		return "service/meet"; 
	}
	
	
	/**
	 * 技术推广
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/service/technology.html"  )
	public String index_technology(ModelMap modelMap){
		String columnCode="2_cy_fw_js";
		ColumnInfo columnInfo=columnInfoRepository.findByColumnCode(columnCode);
		List<ContentInfo> list=contentInfoRepository.findListByColumnId(columnInfo.getColumnId());
		modelMap.put("list", list);
		return "service/technology"; 
	}
	
	
	/**
	 * 产业金融
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/service/finace.html"  )
	public String index_finace(ModelMap modelMap){
		return "service/finace"; 
	}
	
	/**
	 * 会议和新技术详情
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/service/detail.html"  )
	public String detail_news(String contentId, ModelMap modelMap){
		ContentInfo content = contentInfoRepository.findById(contentId).get();
		modelMap.put("content", content);
		return "service/detail"; 
	}

	

	

}
