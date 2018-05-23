package com.szqj.website.control;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.szqj.cms.domain.ColumnInfo;
import com.szqj.cms.domain.ColumnInfoRepository;
import com.szqj.cms.domain.ContentInfo;
import com.szqj.cms.domain.ContentInfoRepository;
import com.szqj.service.domain.Meet;
import com.szqj.service.domain.MeetRepository;
import com.szqj.service.domain.MeetSignUp;
import com.szqj.service.domain.MeetSignUpRepository;
import com.szqj.train.domain.TrainCert;
import com.szqj.train.domain.TrainCertRepository;
import com.szqj.train.domain.TrainTeacher;
import com.szqj.util.Tools;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class ServiceControle {
	

	@Autowired
	private ColumnInfoRepository columnInfoRepository;
	@Autowired
	private ContentInfoRepository contentInfoRepository;
	@Autowired
	private MeetRepository meetRepository;
	
	@Autowired
	private MeetSignUpRepository meetSignUpRepository;
	
	@Autowired
	private TrainCertRepository trainCertRepository;
	
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
	public String cardResult(String userName, String certCode, ModelMap modelMap){
		List<TrainCert> l = trainCertRepository.findByUserNameAndCertCode(userName, certCode);
		if(l!=null&&l.size()>0) {
			modelMap.put("trainCert", l.get(0));
			return "service/cardResult"; 
		}else {
			modelMap.put("flag", 1);
			modelMap.put("userName", userName);
			modelMap.put("certCode", certCode);
			return "service/cardSearch"; 
		}
	}
	
	
	/**
	 * 会议展览
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/service/meet.html"  )
	public String index_meet(Integer pageNum, Integer size, ModelMap modelMap){
		PageRequest pageable=Tools.getPage(pageNum, 5);
		Page<Meet> page=meetRepository.findPage(pageable);
		modelMap.put("page", page);
		return "service/meet"; 
	}
	
	
	@RequestMapping(value = "/service/meet/detail.html"  )
	public String meet_detail(String meetId, ModelMap modelMap){
		Meet meet = meetRepository.findById(meetId).get();
		modelMap.put("meet", meet);
		return "service/meetdetail"; 
	}
	
	@RequestMapping(value = "/service/meet/signup.html"  )
	public String meet_signup(String meetId, ModelMap modelMap){
		Meet meet = meetRepository.findById(meetId).get();
		modelMap.put("meet", meet);
		return "service/meetsignup"; 
	}
	
	@RequestMapping(value = "/service/meet/saveSignup.do"  )
	public String saveSignup(MeetSignUp meetSignUp, ModelMap modelMap){
		meetSignUp.setCreateDate(new Date());
		meetSignUpRepository.save(meetSignUp);
		Meet meet = meetRepository.findById(meetSignUp.getMeetId()).get();
		modelMap.put("meet", meet);
		return "service/meetsignup"; 
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
