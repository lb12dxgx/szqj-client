package com.szqj.weborg.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;

import com.szqj.cms.domain.ColumnInfo;
import com.szqj.cms.domain.ColumnInfoRepository;
import com.szqj.cms.domain.ContentInfo;
import com.szqj.cms.domain.ContentInfoRepository;
import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.EnterpriseRepository;
import com.szqj.service.domain.Product;
import com.szqj.service.domain.ProductRepository;
import com.szqj.service.domain.ZbInfo;
import com.szqj.service.domain.ZbInfoRepository;
import com.szqj.train.domain.TrainPlan;
import com.szqj.train.domain.TrainPlanRepository;
import com.szqj.util.ConstantUtils;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.domain.AccountRepository;
import com.szqj.weborg.domain.Menu;
import com.szqj.weborg.domain.MenuRepository;
import com.szqj.weborg.rest.vo.MenuNode;

import us.codecraft.webmagic.Spider;

/**
 * 
 * @ClassName: AccountService
 * @Description: 账号登录类
 * @author zhanggy
 * 
 *
 */
@Service
@Transactional
public class IndexService {
	
	@Value("${web.upload-path}")
	private String uploadPath;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ColumnInfoRepository columnInfoRepository;
	@Autowired
	private ContentInfoRepository contentInfoRepository;
	@Autowired
	private ZbInfoRepository zbInfoRepository;
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private TrainPlanRepository trainPlanRepository;

	
	
	

	public List<ContentInfo> getBanerNews(){
		ColumnInfo columnInfo=columnInfoRepository.findByColumnCode("1_hy_gg_sy");
		PageRequest pageable=Tools.getPage(0, 10);
		Page<ContentInfo> page=contentInfoRepository.findByColumnId(columnInfo.getColumnId(), pageable);
		return page.getContent();
	}
	
	public List<ContentInfo> getYqNews(){
		ColumnInfo columnInfo=columnInfoRepository.findByColumnCode("3_hy_gg_yq");
		PageRequest pageable=Tools.getPage(0, 30);
		Page<ContentInfo> page=contentInfoRepository.findByColumnId(columnInfo.getColumnId(), pageable);
		return page.getContent();
	}
	
	public List<ContentInfo> getYwNews(){
		ColumnInfo columnInfo=columnInfoRepository.findByColumnCode("1_hy_xw_yw");
		PageRequest pageable=Tools.getPage(0, 2);
		Page<ContentInfo> page=contentInfoRepository.findByColumnId(columnInfo.getColumnId(), pageable);
		return page.getContent();
	}
	
	public List<ContentInfo> getQyNews(){
		ColumnInfo columnInfo=columnInfoRepository.findByColumnCode("2_hy_xw_qy");
		PageRequest pageable=Tools.getPage(0, 5);
		Page<ContentInfo> page=contentInfoRepository.findByColumnId(columnInfo.getColumnId(), pageable);
		return page.getContent();
	}
	
	
	public List<ContentInfo> getGjNews(){
		ColumnInfo columnInfo=columnInfoRepository.findByColumnCode("4_hx_xw_gj");
		PageRequest pageable=Tools.getPage(0, 5);
		Page<ContentInfo> page=contentInfoRepository.findByColumnId(columnInfo.getColumnId(), pageable);
		return page.getContent();
	}
	
	public List<ContentInfo> getSpNews(){
		ColumnInfo columnInfo=columnInfoRepository.findByColumnCode("6_hy_xw_sp");
		PageRequest pageable=Tools.getPage(0, 4);
		Page<ContentInfo> page=contentInfoRepository.findByColumnId(columnInfo.getColumnId(), pageable);
		return page.getContent();
	}
	
	
	public List<ContentInfo> getQkNews(){
		ColumnInfo columnInfo=columnInfoRepository.findByColumnCode("1_dz_zl_qk");
		PageRequest pageable=Tools.getPage(0, 4);
		Page<ContentInfo> page=contentInfoRepository.findByColumnId(columnInfo.getColumnId(), pageable);
		return page.getContent();
	}
	
	
	
	
	public Integer getNumByZc() {
		ColumnInfo columnInfo=columnInfoRepository.findByColumnCode("1_hy_zs_zc");
		Integer num=contentInfoRepository.findNumByColumnId(columnInfo.getColumnId());
		return num;
	}
	
	public Integer getNumByFl() {
		ColumnInfo columnInfo=columnInfoRepository.findByColumnCode("2_hy_zs_fl");
		Integer num=contentInfoRepository.findNumByColumnId(columnInfo.getColumnId());
		return num;
	}
	
	public Integer getNumByBj() {
		ColumnInfo columnInfo=columnInfoRepository.findByColumnCode("3_hy_zs_bj");
		Integer num=contentInfoRepository.findNumByColumnId(columnInfo.getColumnId());
		return num;
	}
	
	public Integer getNumByLw() {
		ColumnInfo columnInfo=columnInfoRepository.findByColumnCode("4_hy_zs_lw");
		Integer num=contentInfoRepository.findNumByColumnId(columnInfo.getColumnId());
		return num;
	}
	
	public List<Product> getProductList(){
		PageRequest pageable=Tools.getPage(0, 4);
		Page<Product> page=productRepository.findPage(pageable);
		return page.getContent();
	}
	
	
	public List<Enterprise> getEnterpriseList(){
		PageRequest pageable=Tools.getPage(0, 4);
		Page<Enterprise> page=enterpriseRepository.findPage(pageable);
		return page.getContent();
	}
	
	public List<ZbInfo> getZbInfoList(){
		PageRequest pageable=Tools.getPage(0, 4);
		Page<ZbInfo> page=zbInfoRepository.findPage(pageable);
		return page.getContent();
	}
	
	
	public List<TrainPlan> getTrainPlanList(){
		List<TrainPlan> l = trainPlanRepository.findMainTrain();
		List<TrainPlan> list = trainPlanRepository.findListTrain();
		if(list!=null&&list.size()>0) {
			l.add(list.get(0));
		}
		return l;
	}
	
	
	
	
	@Scheduled(cron = "0 */5 *  * * * ")
    public void genHtml() { 
		createHtml();
    }
	
	
	private void createHtml() {
		ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8080/index.do", String.class);
		String html=forEntity.getBody();
		
		try {
			FileUtils.write(new File(uploadPath+File.separator+"index.html"), html, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
