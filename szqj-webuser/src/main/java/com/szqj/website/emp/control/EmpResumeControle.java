package com.szqj.website.emp.control;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.EnterpriseRepository;
import com.szqj.service.domain.EnterpriseResume;
import com.szqj.service.domain.EnterpriseResumeRepository;
import com.szqj.service.domain.JobInfo;
import com.szqj.service.domain.JobInfoRepository;
import com.szqj.service.domain.MyPersonRepository;
import com.szqj.service.domain.PerCertInfo;
import com.szqj.service.domain.PerCertInfoRepository;
import com.szqj.service.domain.PerJobInfo;
import com.szqj.service.domain.PerJobInfoRepository;
import com.szqj.service.domain.PerStudyInfo;
import com.szqj.service.domain.PerStudyInfoRepository;
import com.szqj.service.domain.PerTrainInfo;
import com.szqj.service.domain.PerTrainInfoRepository;
import com.szqj.service.domain.Person;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.domain.FileInfo;
import com.szqj.weborg.domain.FileInfoRepository;

/**
 * 企业简历管理
 * @author lb12
 *
 */
@Controller
@RequestMapping("/")
@EnableAutoConfiguration		
public class EmpResumeControle {
	
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	
	@Autowired
	private MyPersonRepository personRepository;
	
	@Autowired
	private PerStudyInfoRepository perStudyInfoRepository;
	@Autowired
	private PerJobInfoRepository perJobInfoRepository;
	@Autowired
	private PerCertInfoRepository perCertInfoRepository;
	@Autowired
	private PerTrainInfoRepository perTrainInfoRepository;
	@Autowired
	private FileInfoRepository fileInfoRepository ;
	@Autowired
	private EnterpriseResumeRepository enterpriseResumeRepository;
	
	
	
	/**
	 * 
	 * 
	 * @param pageNum
	 * @param size
	 * @param workCity 地区
	 * @param hyType 行业
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/emp/resume/search.html")
	public String searchResume(Integer pageNum, Integer size,String workCity,String jobName,ModelMap modelMap){
		PageRequest pageable=Tools.getPage(pageNum, 6);
		Page<Person> page=null;
		
		if(StringUtils.isBlank(workCity)&&(StringUtils.isBlank(jobName))){
			page=personRepository.findEmpPage(pageable);
		}else if(StringUtils.isNotBlank(workCity)&&(StringUtils.isNotBlank(jobName))){
			page=personRepository.findEmpPageByWorkCityAndJobName(workCity, jobName, pageable);
		}else if (StringUtils.isNotBlank(workCity)){
			page=personRepository.findEmpPageByWorkCity(workCity, pageable);
		}else if (StringUtils.isNotBlank(jobName)){
			page=personRepository.findEmpPageByJobName(workCity, pageable);
		}
	
		modelMap.put("page",page);
		modelMap.put("jobName",jobName);
		modelMap.put("workCity",workCity);
		return "emp/resume_search"; 
	}
	
	
	/**
	 * 收到简历信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/emp/resume/adminUn.html")
	public String myUnResume(@SessionAttribute Account account,Integer pageNum, Integer size,String jobName,ModelMap modelMap){
		PageRequest pageable=Tools.getPage(pageNum, 6);
		Enterprise enterprise = enterpriseRepository.findByAccountId(account.getAccountId()).get(0);
		
		Page<EnterpriseResume> page=null;
		if(StringUtils.isBlank(jobName)){
			 page = enterpriseResumeRepository.findPageaByUn(enterprise.getEnterpriseId(), pageable);
		}else{
			page =enterpriseResumeRepository.findPageaByUnAndJobName(enterprise.getEnterpriseId(), jobName, pageable);
		}
		modelMap.put("page", page);
		modelMap.put("jobName", jobName);
		return "emp/resume_myun"; 
	}
	
	
	/**
	 * 收到简历信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/emp/resume/adminFinsh.html")
	public String myFinshResume(@SessionAttribute Account account,Integer pageNum, Integer size,String jobName,ModelMap modelMap){
		PageRequest pageable=Tools.getPage(pageNum, 6);
		
		Enterprise enterprise = enterpriseRepository.findByAccountId(account.getAccountId()).get(0);
		Page<EnterpriseResume> page = enterpriseResumeRepository.findPageaByFinsh(enterprise.getEnterpriseId(), pageable);
		Page<EnterpriseResume> unPage = enterpriseResumeRepository.findPageaByUn(enterprise.getEnterpriseId(), pageable);
		
		modelMap.put("unPage", unPage);
		return "emp/resume_myfinsh"; 
	}
	
	
	/**
	 * 收到简历信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/emp/resume/view.html")
	public String myViewResume(@SessionAttribute Account account,String personId,ModelMap modelMap){
		
		Person person = personRepository.findById(personId).get();
		List<PerStudyInfo> studylist = perStudyInfoRepository.findList(personId);
		List<PerJobInfo> joblist = perJobInfoRepository.findList(personId);
		List<PerCertInfo> certlist = perCertInfoRepository.findList(personId);
		List<PerTrainInfo> trainlist = perTrainInfoRepository.findList(personId);
		
		modelMap.put("trainlist", trainlist);
		modelMap.put("certlist", certlist);
		modelMap.put("joblist", joblist);
		modelMap.put("studylist", studylist);
		modelMap.put("person", addWorkYearAndPerNum(person));
		
		List<FileInfo> files = fileInfoRepository.findByBussinessId(person.getPersonPicId());
		if(files!=null&&files.size()>0) {
			modelMap.put("fileWebPath", files.get(0).getFileWebPath());
		}
		
		
		List<Person> toplist = personRepository.findTopList();
		
		modelMap.put("toplist", toplist);
		
		return "emp/resume_myview"; 
	}
	
	
	private Person addWorkYearAndPerNum(Person person){
	    Calendar cal = Calendar.getInstance();
	    int yearNow = cal.get(Calendar.YEAR); 
	    cal.setTime(person.getBirthDate());
	    int yearBirth = cal.get(Calendar.YEAR); 
	    cal.setTime(person.getJobDate());
	    int yearJob = cal.get(Calendar.YEAR); 
		person.setPerNum(yearNow-yearBirth);
		person.setWorkYear(yearNow-yearJob);
		return person;
	}

}
