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
public class NewsControle {
	
	@Autowired
	private ColumnInfoRepository columnInfoRepository;
	@Autowired
	private ContentInfoRepository contentInfoRepository;
	
	/**
	 * 产业资讯
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/news/index.html"  )
	public String index_news(String columnCode,Integer pageNum, Integer size, ModelMap modelMap){
		
		ColumnInfo pcolumnInfo=columnInfoRepository.findByColumnCode("1_hy_xw");
		List<ColumnInfo> columnInfoList = columnInfoRepository.findByParentId(pcolumnInfo.getColumnId());
		if(StringUtils.isBlank(columnCode)) {
			columnCode="1_hy_xw_yw";
		}
		
		ColumnInfo columnInfo=columnInfoRepository.findByColumnCode(columnCode);
		PageRequest pageable=Tools.getPage(pageNum, 5);
		Page<ContentInfo> page=contentInfoRepository.findByColumnId(columnInfo.getColumnId(), pageable);
		
		modelMap.put("columnInfoList", columnInfoList);
		modelMap.put("page", page);
		modelMap.put("columnCode", columnCode);
		
		List<ContentInfo> ywcontentInfos = contentInfoRepository.findByParntIdAndLevel(pcolumnInfo.getColumnId(), 90);
		List<ContentInfo> ydcontentInfos = contentInfoRepository.findByParntIdAndLevel(pcolumnInfo.getColumnId(), 80);
		modelMap.put("ywcontentInfos", ywcontentInfos);
		modelMap.put("ydcontentInfos", ydcontentInfos);
		
		setGG(modelMap,pcolumnInfo);
		
		return "news/index"; 
	}


	private void setGG(ModelMap modelMap,ColumnInfo pcolumnInfo) {
		
		List<ContentInfo> ywcontentInfos = contentInfoRepository.findByParntIdAndLevel(pcolumnInfo.getColumnId(), 90);
		List<ContentInfo> ydcontentInfos = contentInfoRepository.findByParntIdAndLevel(pcolumnInfo.getColumnId(), 80);
		modelMap.put("ywcontentInfos", ywcontentInfos);
		modelMap.put("ydcontentInfos", ydcontentInfos);
		
		ColumnInfo ggcolumnInfo=columnInfoRepository.findByColumnCode("2_hy_gg_zx");
		
		List<ContentInfo> ggcontentInfoone = contentInfoRepository.findByColumnIdAndLevel(ggcolumnInfo.getColumnId(), 90);
		if(ggcontentInfoone!=null&&ggcontentInfoone.size()>0) {
			
			modelMap.put("ggone", ggcontentInfoone.get(0));
		}
		
		List<ContentInfo> ggcontentInfotwo = contentInfoRepository.findByColumnIdAndLevel(ggcolumnInfo.getColumnId(), 80);
		if(ggcontentInfotwo!=null&&ggcontentInfotwo.size()>0) {
			
			modelMap.put("ggtwo", ggcontentInfotwo.get(0));
		}
	}
	
	
	/**
	 * 产业资讯详情
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/news/detail.html"  )
	public String detail_news(String contentId, ModelMap modelMap){
		ColumnInfo pcolumnInfo=columnInfoRepository.findByColumnCode("1_hy_xw");
		setGG(modelMap,pcolumnInfo);
		ContentInfo content = contentInfoRepository.findById(contentId).get();
		modelMap.put("content", content);
		return "news/detail"; 
	}
	
	
	/**
	 * 产业资讯详情
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/news/videodetail.html"  )
	public String videodetail_news(String contentId, ModelMap modelMap){
		ColumnInfo pcolumnInfo=columnInfoRepository.findByColumnCode("1_hy_xw");
		setGG(modelMap,pcolumnInfo);
		ContentInfo content = contentInfoRepository.findById(contentId).get();
		modelMap.put("content", content);
		return "news/videodetail"; 
	}
	
	
	
	

	

	

}
