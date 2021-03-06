package com.szqj.website.control;

import java.util.Date;
import java.util.HashMap;
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
import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.EnterpriseCert;
import com.szqj.service.domain.EnterpriseCertRepository;
import com.szqj.service.domain.EnterpriseRepository;
import com.szqj.service.domain.Meet;
import com.szqj.service.domain.MeetRepository;
import com.szqj.service.domain.MeetSignUp;
import com.szqj.service.domain.MeetSignUpRepository;
import com.szqj.service.domain.Product;
import com.szqj.service.domain.ProductRepository;
import com.szqj.service.domain.ZbInfo;
import com.szqj.service.domain.ZbInfoRepository;
import com.szqj.springmvc.Token;
import com.szqj.train.domain.TrainCert;
import com.szqj.train.domain.TrainCertRepository;
import com.szqj.train.domain.TrainTeacher;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.FileInfo;
import com.szqj.weborg.domain.FileInfoRepository;

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
	
	@Autowired
	private ZbInfoRepository zbInfoRepository;
	
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private FileInfoRepository fileInfoRepository;
	
	@Autowired
	private  EnterpriseCertRepository enterpriseCertRepository;
	
	
	
	/**
	 * 企业信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/service/enterprise.html"  )
	public String index_enterprise(Integer pageNum, Integer size, ModelMap modelMap){
		PageRequest pageable=Tools.getPage(pageNum, 5);
		Page<Enterprise> page=enterpriseRepository.findPage(pageable);
		modelMap.put("page", page);
		return "service/enterprise"; 
	}
	
	
	@RequestMapping(value = "/service/enterprise/detail.html"  )
	public String enterprise_detail(String enterpriseId, ModelMap modelMap){
		Enterprise enterprise = enterpriseRepository.findById(enterpriseId).get();
		modelMap.put("enterprise", enterprise);
		return "service/enterprisedetail"; 
	}
	
	
	/**
	 * 产品信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/service/product.html"  )
	public String index_product(Integer pageNum, Integer size, ModelMap modelMap){
		PageRequest pageable=Tools.getPage(pageNum, 5);
		Page<Product> page=productRepository.findPage(pageable);
		for(Product product:page.getContent()){
			Enterprise enterprise =enterpriseRepository.findById(product.getEnterpriseId()).get();
			product.setEmpName(enterprise.getEnterpriseName());
			product.setProductAddr(enterprise.getAddree());
		}
		modelMap.put("page", page);
		return "service/product"; 
	}
	
	
	@RequestMapping(value = "/service/product/detail.html"  )
	public String product_detail(String productId, ModelMap modelMap){
		Product product = productRepository.findById(productId).get();
		Enterprise enterprise =enterpriseRepository.findById(product.getEnterpriseId()).get();
		product.setEmpName(enterprise.getEnterpriseName());
		product.setProductAddr(enterprise.getAddree());
		List<FileInfo> list = fileInfoRepository.findByBussinessId(product.getProductPicId());
		modelMap.put("product", product);
		modelMap.put("list", list);
		return "service/productdetail"; 
	}
	
	/**
	 * 招标信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/service/zbinfo.html"  )
	public String index_zbinfo(Integer pageNum, Integer size, ModelMap modelMap){
		PageRequest pageable=Tools.getPage(pageNum, 5);
		Page<ZbInfo> page=zbInfoRepository.findPage(pageable);
		modelMap.put("page", page);
		return "service/zbinfo"; 
	}
	
	
	@RequestMapping(value = "/service/zbinfo/detail.html"  )
	public String zbinfo_detail(String zbInfoId, ModelMap modelMap){
		ZbInfo zbInfo = zbInfoRepository.findById(zbInfoId).get();
		modelMap.put("zbInfo", zbInfo);
		return "service/zbinfodetail"; 
	}
	
	
	
	
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
	 * 企业电子证书查询
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/service/cardSearchEnteg.html"  )
	public String cardSearchEnteg(ModelMap modelMap){
		return "service/cardSearchEnteg"; 
	}
	
	
	/**
	 * 企业电子证书结果
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/service/cardResultEnteg.html"  )
	public String cardResultEnteg(String  enterpriseName, String certCode, ModelMap modelMap){
		List<EnterpriseCert> l = enterpriseCertRepository.findByEnterpriseNameAndCertCode(enterpriseName, certCode);
		if(l!=null&&l.size()>0) {
			modelMap.put("enterpriseCert", l.get(0));
			return "service/cardResultEnteg_"+l.get(0).getCertTypeName(); 
		}else {
			modelMap.put("flag", 1);
			modelMap.put("enterpriseName", enterpriseName);
			modelMap.put("certCode", certCode);
			return "service/cardSearchEnteg"; 
		}
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
		
		List<Meet> l = page.getContent();
		HashMap map=new HashMap();
		for(Meet c:l) {
			String tFiled = c.getMeetPicId();
			List<FileInfo> files = fileInfoRepository.findByBussinessId(tFiled);
			if(files!=null&&files.size()>0) {
				map.put(tFiled, files.get(0).getFileWebPath());
			}
		}
		
		modelMap.put("page", page);
		modelMap.put("fileMap", map);
		return "service/meet"; 
	}
	
	
	@RequestMapping(value = "/service/meet/detail.html"  )
	public String meet_detail(String meetId, ModelMap modelMap){
		Meet meet = meetRepository.findById(meetId).get();
		
		
		String tFiled = meet.getMeetPicId();
		List<FileInfo> files = fileInfoRepository.findByBussinessId(tFiled);
		if(files!=null&&files.size()>0) {
			modelMap.put("meetpath", files.get(0).getFileWebPath());
		}
		
		modelMap.put("meet", meet);
		return "service/meetdetail"; 
	}
	
	@Token(save = true)
	@RequestMapping(value = "/service/meet/sign.html"  )
	public String meet_sign(String meetId, ModelMap modelMap){
		 List<Meet> meets = meetRepository.findByIsSign();
		 if(meets.size()>0&&meets!=null){
			 modelMap.put("meet", meets.get(0));
		 }else{
			 modelMap.put("message", "无待签到会议!");
		 }
		return "service/weixin/meetsign"; 
	}
	
	@Token(remove = true)
	@RequestMapping(value = "/service/meet/saveSign.do"  )
	public String saveSign(String meetId,String telphone, ModelMap modelMap){
		
		List<MeetSignUp> l = meetSignUpRepository.findListByMeetIdAndTelphone(meetId, telphone);
	    if(l==null||l.size()==0){
	    	modelMap.put("message", telphone+"未报名参加会议，请核对手机号重新输入!");
	    }else{
	    	MeetSignUp meetSignUp=l.get(0);
	    	if(meetSignUp.getIsSign()==1){
	    		modelMap.put("mesg", meetSignUp.getUserName()+"已经签到会议，请勿重复签到!");
	    	}else{
	    		meetSignUp.setIsSign(1);
	    		meetSignUpRepository.save(meetSignUp);
	    		modelMap.put("message",  meetSignUp.getUserName()+"已经签成功!");
	    	}
	    }
		
		return "service/weixin/signsuccess"; 
	}
	
	
	@Token(save = true)
	@RequestMapping(value = "/nh.html"  )
	public String meet_signup_nh( ModelMap modelMap){
		Meet meet = meetRepository.findById("5d12c0d3-9854-4e91-8420-1079b208e49e").get();
		modelMap.put("meet", meet);
		return "service/meetsignup"; 
	}
	

	@RequestMapping(value = "/wnh.html"  )
	public String meet_signup_wnh( ModelMap modelMap){
		
		return "redirect:app/index.html#/meetSignUp"; 
	}
	
	
	@Token(save = true)
	@RequestMapping(value = "/service/meet/signup.html"  )
	public String meet_signup(String meetId, ModelMap modelMap){
		Meet meet = meetRepository.findById(meetId).get();
		modelMap.put("meet", meet);
		return "service/meetsignup"; 
	}
	
	@Token(remove = true)
	@RequestMapping(value = "/service/meet/saveSignup.do"  )
	public String saveSignup(MeetSignUp meetSignUp, ModelMap modelMap){
		meetSignUp.setCreateDate(new Date());
		meetSignUpRepository.save(meetSignUp);
		Meet meet = meetRepository.findById(meetSignUp.getMeetId()).get();
		modelMap.put("meet", meet);
		return "service/signupsuccess"; 
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
