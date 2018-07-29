package com.szqj.website.control;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import com.szqj.service.domain.EnterpriseRepository;
import com.szqj.service.domain.JobInfo;
import com.szqj.service.domain.JobInfoRepository;
import com.szqj.service.domain.MyPersonRepository;
import com.szqj.service.domain.Person;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.FileInfo;
import com.szqj.weborg.domain.FileInfoRepository;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class JobControle {
	
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	@Autowired
	private MyPersonRepository personRepository;
	
	@Autowired
	private JobInfoRepository jobInfoRepository;
	@Autowired
	private FileInfoRepository fileInfoRepository;
	
	/**
	 * 行业招聘
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/job/index.html"  )
	public String index(ModelMap modelMap){
		mainEmp(modelMap);
		newJob(modelMap);
		topResum(modelMap);
		return "job/index"; 
	}
	
	@RequestMapping(value = "/job/joblist.html"  )
	public String job_list(Integer pageNum, Integer size, ModelMap modelMap) {
		PageRequest pageable=Tools.getPage(pageNum, 8);
		Page<JobInfo> page=jobInfoRepository.findTopPage(pageable);
		List<JobInfo> l = page.getContent();
		for(JobInfo jobInfo:l) {
			String enterpriseId = jobInfo.getEnterpriseId();
			Enterprise enterprise = enterpriseRepository.findById(enterpriseId).get();
			jobInfo.setEnterpriseName(enterprise.getEnterpriseName());
		}
		modelMap.put("page",page);
		return "job/job_list"; 
	}
	
	@RequestMapping(value = "/job/jobview.html"  )
	public String job_view(String jobInfoId , ModelMap modelMap) {
		JobInfo jobInfo = jobInfoRepository.findById(jobInfoId).get();
		Enterprise enterprise=enterpriseRepository.findById(jobInfo.getEnterpriseId()).get();
		modelMap.put("jobInfo",jobInfo);
		modelMap.put("enterprise",enterprise);
		
		PageRequest pageable=Tools.getPage(0, 4);
		Page<JobInfo> page=jobInfoRepository.findTopPage(pageable);
		List<JobInfo> l = page.getContent();
		for(JobInfo job:l) {
			String enterpriseId = job.getEnterpriseId();
			Enterprise ent = enterpriseRepository.findById(enterpriseId).get();
			job.setEnterpriseName(ent.getEnterpriseName());
		}
		modelMap.put("joblist",l);
		
		return "job/job_view"; 
	}
	
	@RequestMapping(value = "/job/resumelist.html"  )
	public String resume_list(Integer pageNum, Integer size,ModelMap modelMap) {
		PageRequest pageable=Tools.getPage(pageNum, 8);
		Page<Person> page = personRepository.findPage(pageable);
		for(Person person:page.getContent()) {
			String personName=person.getPersonName();
			personName=personName.substring(0, 1);
			if("男".equals(person.getPersonSex())) {
				personName=personName+"先生";
			}else {
				personName=personName+"女士";
			}
			person.setPersonName(personName);
		}
		modelMap.put("page",page);
		
		return "job/resume_list"; 
	}
	
	
	@RequestMapping(value = "/job/resumeview.html"  )
	public String resume_view(String personId , ModelMap modelMap) {
		Person person = personRepository.findById(personId).get();
		modelMap.put("person",person);
		return "job/job_view"; 
	}
	
	/**
	 * 名企招聘
	 * @param modelMap
	 * @return
	 */
	private void mainEmp(ModelMap modelMap) {
		PageRequest pageable=Tools.getPage(0, 8);
		Page<Enterprise> page = enterpriseRepository.findPageByJobLevel(pageable);
		modelMap.put("mainEmpList", page.getContent());
		HashMap fileMap=new HashMap();
		initMap(fileMap,page);
		modelMap.put("fileMap", fileMap);
	}
	
	/**
	 * 推荐职位
	 * @param modelMap
	 */
	private void newJob(ModelMap modelMap) {
		PageRequest pageable=Tools.getPage(0, 8);
		Page<JobInfo> page = jobInfoRepository.findTopPage(pageable);
		List<JobInfo> l = page.getContent();
		for(JobInfo jobInfo:l) {
			String enterpriseId = jobInfo.getEnterpriseId();
			Enterprise enterprise = enterpriseRepository.findById(enterpriseId).get();
			jobInfo.setEnterpriseName(enterprise.getEnterpriseName());
		}
		modelMap.put("newJobList",l);
	}
	
	
	/**
	 * 推荐简历
	 * @param modelMap
	 */
	private void topResum(ModelMap modelMap) {
		PageRequest pageable=Tools.getPage(0, 4);
		Page<Person> page = personRepository.findPage(pageable);
		for(Person person:page.getContent()) {
			String personName=person.getPersonName();
			personName=personName.substring(0, 1);
			if("男".equals(person.getPersonSex())) {
				personName=personName+"先生";
			}else {
				personName=personName+"女士";
			}
			person.setPersonName(personName);
		}
		modelMap.put("topResumList",page.getContent());
	}
	
	private void initMap(HashMap map, Page<Enterprise> page) {
		List<Enterprise> l = page.getContent();
		for(Enterprise c:l) {
			String tFiled = c.getEnterprisePicId();
			List<FileInfo> files = fileInfoRepository.findByBussinessId(tFiled);
			if(files!=null&&files.size()>0) {
				map.put(tFiled, files.get(0).getFileWebPath());
			}
		}
	}


}
