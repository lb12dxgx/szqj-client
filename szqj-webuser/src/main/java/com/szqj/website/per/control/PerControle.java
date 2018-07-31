package com.szqj.website.per.control;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.szqj.service.domain.MyPersonRepository;
import com.szqj.service.domain.Person;
import com.szqj.util.Tools;
import com.szqj.weborg.domain.Account;
import com.szqj.weborg.domain.Dict;
import com.szqj.weborg.domain.DictRepository;
import com.szqj.weborg.domain.FileInfo;
import com.szqj.weborg.domain.FileInfoRepository;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class PerControle {
	
	
	
	@Autowired
	private MyPersonRepository personRepository;
	
	@Autowired
	private FileInfoRepository fileInfoRepository;
	@Autowired
	private DictRepository dictRepository;
	
	
	
	/**
	 * 个人信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/per/info.html"  )
	public String info(ModelMap modelMap,HttpServletRequest request){
		Account account = (Account)request.getSession().getAttribute("account");
		List<Person> persons = personRepository.findByAccountId(account.getAccountId());
		List<FileInfo> files = fileInfoRepository.findByBussinessId(persons.get(0).getPersonPicId());
		if(files!=null&&files.size()>0) {
			modelMap.put("fileWebPath", files.get(0).getFileWebPath());
		}
		modelMap.put("person", persons.get(0));
		return "per/info"; 
	}
	
	
	/**
	 * 企业信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/per/save.do"  ) 
	public String save(Person person, ModelMap modelMap){
		Person personRet = personRepository.findById(person.getPersonId()).get();
		Tools.copyBeanForUpdate(person, personRet);
	
		personRepository.save(personRet);
		return "redirect:/per/info.html"; 
	}
	
	/**
	 * 个人信息
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/per/resume.html"  )
	public String resume(ModelMap modelMap,HttpServletRequest request){
		Account account = (Account)request.getSession().getAttribute("account");
		List<Person> persons = personRepository.findByAccountId(account.getAccountId());
		List<FileInfo> files = fileInfoRepository.findByBussinessId(persons.get(0).getPersonPicId());
		if(files!=null&&files.size()>0) {
			modelMap.put("fileWebPath", files.get(0).getFileWebPath());
		}
		modelMap.put("person", addWorkYearAndPerNum(persons.get(0)));
		return "per/resume"; 
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
	
	public static  int getAge(Date birthDay) throws Exception {  
        Calendar cal = Calendar.getInstance();  
        if (cal.before(birthDay)) {  
            throw new IllegalArgumentException(  
                    "The birthDay is before Now.It's unbelievable!");  
        }  
        int yearNow = cal.get(Calendar.YEAR);  
        int monthNow = cal.get(Calendar.MONTH);  
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);  
        cal.setTime(birthDay);   
  
        int yearBirth = cal.get(Calendar.YEAR);  
        int monthBirth = cal.get(Calendar.MONTH);  
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);   
  
        int age = yearNow - yearBirth;  
  
        if (monthNow <= monthBirth) {  
            if (monthNow == monthBirth) {  
                if (dayOfMonthNow < dayOfMonthBirth) age--;  
            }else{  
                age--;  
            }  
        }  
        return age;  
    }  
	
	
	/**
	 * 简历基本信息修改
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/per/infoedit.html"  )
	public String infoedit(ModelMap modelMap,HttpServletRequest request){
		Account account = (Account)request.getSession().getAttribute("account");
		List<Person> persons = personRepository.findByAccountId(account.getAccountId());
		modelMap.put("person", persons.get(0));
		List<FileInfo> files = fileInfoRepository.findByBussinessId(persons.get(0).getPersonPicId());
		if(files!=null&&files.size()>0) {
			modelMap.put("fileWebPath", files.get(0).getFileWebPath());
		}
		return "per/infoedit"; 
	}
	
	/**
	 * 简历基本信息保存
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/per/infosave.do"  )
	public String infosave(ModelMap modelMap,HttpServletRequest request,Person person){
		
		String hyType = dictRepository.findByDictValue(person.getHyTypeCode()).get(0).getDictName();
		String perSalary = dictRepository.findByDictValue(person.getPerSalaryCode()).get(0).getDictName();
		String jobStudy = dictRepository.findByDictValue(person.getJobStudyCode()).get(0).getDictName();
		String workState = dictRepository.findByDictValue(person.getWorkStateCode()).get(0).getDictName();
	
		Person personRet = personRepository.findById(person.getPersonId()).get();
		
		Tools.copyBeanForUpdate(person, personRet);
		personRet.setHyType(hyType);
		personRet.setPerSalary(perSalary);
		personRet.setJobStudy(jobStudy);
		personRet.setWorkState(workState);
		personRet.setUpdateDate(new Date());
		
		personRepository.save(personRet);
		
		modelMap.put("person", personRet);
		return "redirect:/per/infoview.html"; 
	}
	
	
	/**
	 * 简历基本信息查看
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/per/infoview.html"  )
	public String infoview(ModelMap modelMap,HttpServletRequest request){
		Account account = (Account)request.getSession().getAttribute("account");
		List<Person> persons = personRepository.findByAccountId(account.getAccountId());
		modelMap.put("person", persons.get(0));
		List<FileInfo> files = fileInfoRepository.findByBussinessId(persons.get(0).getPersonPicId());
		if(files!=null&&files.size()>0) {
			modelMap.put("fileWebPath", files.get(0).getFileWebPath());
		}
		return "per/infoview"; 
	}
	
	
	/**
	 * 更新简历基本信
	 * @param pageNum
	 * @param size
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/per/resumupadte.html"  )
	public String resumupadte(ModelMap modelMap,HttpServletRequest request){
		Account account = (Account)request.getSession().getAttribute("account");
		List<Person> persons = personRepository.findByAccountId(account.getAccountId());
		Person person = persons.get(0);
		person.setUpdateDate(new Date());
		personRepository.save(person);
		return "redirect:/per/info.html"; 
	}
	
}
