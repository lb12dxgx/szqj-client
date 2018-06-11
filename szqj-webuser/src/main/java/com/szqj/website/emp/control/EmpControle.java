package com.szqj.website.emp.control;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.szqj.reg.domain.RegInfo;
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
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.domain.FileInfo;
import com.szqj.weborg.domain.FileInfoRepository;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class EmpControle {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	
	/**
	 * 企业信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/emp/info.html"  )
	public String info(ModelMap modelMap,HttpServletRequest request){
		Account account = (Account)request.getSession().getAttribute("account");
		List<Enterprise> enterprises = enterpriseRepository.findByAccountId(account.getAccountId());
		modelMap.put("enterprise", enterprises.get(0));
		return "emp/info"; 
	}
	
	
	/**
	 * 企业信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/emp/save.do"  )
	public String info(Enterprise enterprise, ModelMap modelMap){
		Enterprise enterpriseRet = enterpriseRepository.findById(enterprise.getEnterpriseId()).get()
		Tools.copyBeanForUpdate(enterprise, enterpriseRet);
		enterpriseRepository.save(enterpriseRet);
		return "emp/index"; 
	}
	
	

	/**
	 * 人员信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/emp/user.html"  )
	public String user(Integer pageNum, Integer size, ModelMap modelMap){
		PageRequest pageable=Tools.getPage(pageNum, 5);
		Page<Product> page=productRepository.findPage(pageable);
		
		modelMap.put("page", page);
		return "emp/user"; 
	}
	
	
	/**
	 * 人员信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/emp/user/add.html"  )
	public String userAdd(Integer pageNum, Integer size, ModelMap modelMap){
		PageRequest pageable=Tools.getPage(pageNum, 5);
		Page<Product> page=productRepository.findPage(pageable);
		
		modelMap.put("page", page);
		return "emp/user_add"; 
	}
	
	
	/**
	 * 人员信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/emp/user/save.html"  )
	public String userSave(Integer pageNum, Integer size, ModelMap modelMap){
		PageRequest pageable=Tools.getPage(pageNum, 5);
		Page<Product> page=productRepository.findPage(pageable);
		
		modelMap.put("page", page);
		return "emp/user_add"; 
	}
	
	/**
	 * 人员信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/emp/user/edit.html"  )
	public String userEdit(Integer pageNum, Integer size, ModelMap modelMap){
		PageRequest pageable=Tools.getPage(pageNum, 5);
		Page<Product> page=productRepository.findPage(pageable);
		
		modelMap.put("page", page);
		return "emp/user_edit"; 
	}
	
	
	
	
	
	/**
	 * 产品信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/emp/productinfo.html"  )
	public String productinfo(Integer pageNum, Integer size, ModelMap modelMap){
		PageRequest pageable=Tools.getPage(pageNum, 5);
		Page<Product> page=productRepository.findPage(pageable);
		
		modelMap.put("page", page);
		return "emp/productinfo"; 
	}
	
	
	/**
	 * 培训信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/emp/meet.html"  )
	public String meet(Integer pageNum, Integer size, ModelMap modelMap){
		PageRequest pageable=Tools.getPage(pageNum, 5);
		Page<Product> page=productRepository.findPage(pageable);
		
		modelMap.put("page", page);
		return "emp/meet"; 
	}
	
	
	
	/**
	 * 培训信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/emp/traininfo.html"  )
	public String traininfo(Integer pageNum, Integer size, ModelMap modelMap){
		PageRequest pageable=Tools.getPage(pageNum, 5);
		Page<Product> page=productRepository.findPage(pageable);
		
		modelMap.put("page", page);
		return "emp/traininfo"; 
	}
	
	/**
	 * 招聘信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/emp/jobinfo.html"  )
	public String jobinfo(Integer pageNum, Integer size, ModelMap modelMap){
		PageRequest pageable=Tools.getPage(pageNum, 5);
		Page<Product> page=productRepository.findPage(pageable);
		
		modelMap.put("page", page);
		return "emp/jobinfo"; 
	}
	
	
	/**
	 * 采购信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/emp/buyinfo.html"  )
	public String purchase(Integer pageNum, Integer size, ModelMap modelMap){
		PageRequest pageable=Tools.getPage(pageNum, 5);
		Page<Product> page=productRepository.findPage(pageable);
		
		modelMap.put("page", page);
		return "emp/buyinfo"; 
	}
	
	
	

}
