package com.szqj.website.control;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.szqj.cms.domain.ColumnInfo;
import com.szqj.cms.domain.ColumnInfoRepository;
import com.szqj.cms.domain.ContentInfo;
import com.szqj.cms.domain.ContentInfoRepository;
import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.EnterpriseRepository;
import com.szqj.service.domain.EnterpriseResume;
import com.szqj.service.domain.EnterpriseResumeRepository;
import com.szqj.service.domain.JobInfo;
import com.szqj.service.domain.JobInfoRepository;
import com.szqj.service.domain.MyPersonRepository;
import com.szqj.service.domain.Person;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
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
	
	@Autowired
	private EnterpriseResumeRepository enterpriseResumeRepository;
	
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
	public String job_list(Integer pageNum, Integer size, String jobName,String place,ModelMap modelMap) {
		PageRequest pageable=Tools.getPage(pageNum, 8);
		Page<JobInfo> page=null;
		if(StringUtils.isBlank(jobName)&&StringUtils.isBlank(place)){
		 page=jobInfoRepository.findPage(pageable);
		}else if (StringUtils.isNotBlank(jobName)&&StringUtils.isNotBlank(place)){
			 page=jobInfoRepository.findAllPageByPlaceAndPlace(jobName,place, pageable);
		}else if(StringUtils.isNotBlank(place)){
			 page=jobInfoRepository.findAllPageByPlace(jobName, pageable);
		}else if(StringUtils.isNotBlank(jobName)){
			 page=jobInfoRepository.findAllPageByJobInfoName(jobName, pageable);
		}
		List<JobInfo> l = page.getContent();
		for(JobInfo jobInfo:l) {
			String enterpriseId = jobInfo.getEnterpriseId();
			Enterprise enterprise = enterpriseRepository.findById(enterpriseId).get();
			jobInfo.setEnterpriseName(enterprise.getEnterpriseName());
		}
		modelMap.put("page",page);
		modelMap.put("jobName",jobName);
		modelMap.put("place",place);
		return "job/job_list"; 
	}
	
	@RequestMapping(value = "/job/jobemp.html"  )
	public String job_emp(String enterpriseId,Integer pageNum, Integer size, ModelMap modelMap) {
	
		Enterprise enterprise=enterpriseRepository.findById(enterpriseId).get();
		modelMap.put("enterprise",enterprise);
		
		PageRequest pageable=Tools.getPage(pageNum, size);
		Page<JobInfo> page=jobInfoRepository.findPageByEnterpriseId(enterpriseId,pageable);
		modelMap.put("page",page);
		
		PageRequest toppageable=Tools.getPage(0, 4);
		Page<JobInfo> toppage=jobInfoRepository.findTopPage(toppageable);
		List<JobInfo> l = toppage.getContent();
		for(JobInfo job:l) {
			Enterprise ent = enterpriseRepository.findById(job.getEnterpriseId()).get();
			job.setEnterpriseName(ent.getEnterpriseName());
		}
		modelMap.put("joblist",l);
		return "job/job_emp"; 
	}
	
	@RequestMapping(value = "/job/jobview.html"  )
	public String job_view(String jobInfoId , ModelMap modelMap,HttpServletRequest request) {
		Object accountob = request.getSession().getAttribute("account");
		
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
		
		
		//判断个人是否有简历和是否已经投过简历
		if(accountob!=null){
			Account account=(Account)accountob;
			List<Person> persons = personRepository.findByAccountId(account.getAccountId());
			Person person=persons.get(0);
			if(person.getUpdateDate()!=null){
				modelMap.put("hasResume",true);
			}else{
				modelMap.put("hasResume",false);
			}
			
			List<EnterpriseResume> list = enterpriseResumeRepository.findByJobInfoAndPerson(jobInfo.getJobInfoId(),person.getPersonId());
			if(list!=null&list.size()>0){
				modelMap.put("hasJob",true);
			}
			
			modelMap.put("accountType",account.getAccountType());
		}else{
			modelMap.put("accountType","");
		}
		
		return "job/job_view"; 
	}
	
	@RequestMapping(value = "/job/applayJob.do"  )
    public String applay_Job(@SessionAttribute Account account,String jobInfoId,String enterpriseId){
		List<Person> persons = personRepository.findByAccountId(account.getAccountId());
		
		JobInfo jobInfo = jobInfoRepository.findById(jobInfoId).get();
		Enterprise ent = enterpriseRepository.findById(enterpriseId).get();
		EnterpriseResume empResume=new EnterpriseResume();
		empResume.setCreateDate(new Date());
		empResume.setEnterprise(ent);
		empResume.setJobInfo(jobInfo);
		empResume.setPerson(persons.get(0));
		
		enterpriseResumeRepository.save(empResume);
		
		return "redirect:/job/jobview.html?jobInfoId="+jobInfoId; 
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
		
		List<Person> list = personRepository.findTopList();
		for(Person person:list) {
			String personName=person.getPersonName();
			personName=personName.substring(0, 1);
			if("男".equals(person.getPersonSex())) {
				personName=personName+"先生";
			}else {
				personName=personName+"女士";
			}
			person.setPersonName(personName);
		}
		modelMap.put("topResumList",list);
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
