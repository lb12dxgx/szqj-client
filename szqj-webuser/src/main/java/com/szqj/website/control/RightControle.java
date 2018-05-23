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
import com.szqj.service.domain.Meet;
import com.szqj.service.domain.MeetRepository;
import com.szqj.util.Tools;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class RightControle {
	
	@Autowired
	private ColumnInfoRepository columnInfoRepository;
	@Autowired
	private ContentInfoRepository contentInfoRepository;
	
	@Autowired
	private MeetRepository meetRepository;
	
	/**
	 * 产业资讯
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "right.html"  )
	public String index_right(ModelMap modelMap){
		
		PageRequest pageable=Tools.getPage(0, 5);
		Page<Meet> page=meetRepository.findPage(pageable);
		modelMap.put("meetList", page.getContent());
		
		ColumnInfo pcolumnInfo=columnInfoRepository.findByColumnCode("1_hy_xw");
		List<ContentInfo> ywcontentInfos = contentInfoRepository.findByParntIdAndLevel(pcolumnInfo.getColumnId(), 90);
		modelMap.put("ywcontentInfos", ywcontentInfos);
		
		
		setGG(modelMap);
		
		return "news/right"; 
	}


	private void setGG(ModelMap modelMap) {
		
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
	
	
	
	
	

	

	

}
