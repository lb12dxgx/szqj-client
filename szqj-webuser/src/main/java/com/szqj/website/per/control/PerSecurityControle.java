package com.szqj.website.per.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.szqj.reg.service.SecurityService;
import com.szqj.service.domain.MyPersonRepository;
import com.szqj.service.domain.Person;
import com.szqj.springmvc.Token;
import com.szqj.util.RestJson;
import com.szqj.weborg.domain.Account;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class PerSecurityControle {
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired 
	private MyPersonRepository personRepository;

	/**
	 * 个人 安全信息
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/per/security.html"  )
	public String index_security(){
		return "per/security"; 
	}
	
	
	/**
	 * 进入密码重新设置页面
	 * @param modelMap
	 * @return
	 */
	@Token(save = true)
	@RequestMapping(value = "/per/changepassword.html"  )
	public String change_password(){
		return "per/changepassword"; 
	}
	
	
	/**
	 * 验证原先密码
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/per/repassword.html"  )
	public boolean re_password(@SessionAttribute Account account,String oldpassword){
		boolean flag=securityService.repassword(account.getAccountId(),oldpassword);
		return flag;
	}
	
	
	/**
	 * 保存密码
	 * @param modelMap
	 * @return
	 */
	@Token(remove = true)
	@RequestMapping(value = "/per/savepassword.do"  )
	public String save_password(@SessionAttribute Account account,String password,String oldpassword){
		boolean flag=securityService.repassword(account.getAccountId(),oldpassword);
		if(flag){
			securityService.savePassWord(account.getAccountId(),password,oldpassword);
			return "redirect:/per/security.html"; 
		}else{
			return "per/changepassword"; 
		}
	}
	
	
	/**
	 * 进入 手机号重新设置页面
	 * @param modelMap
	 * @return
	 */
	@Token(save = true)
	@RequestMapping(value = "/per/changetelphone.html"  )
	public String change_pertelphone(@SessionAttribute Account account,ModelMap modelMap){
		List<Person> l = personRepository.findByAccountId(account.getAccountId());
		modelMap.put("oldtelphone", l.get(0).getTelePhone());
		return "per/changetelphone"; 
	}
	
	
	/**
	 * 发送手机修改验证码
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/per/sendsms.html")
	public RestJson sendSmsCode(String oldtelphone,HttpServletRequest request){
		String changetelcode = securityService.sendSmsCode(oldtelphone);
		request.getSession().setAttribute("changetelcode", changetelcode);
		return RestJson.createSucces();
	}
	
	/**
	 * 判断新的手机号码是否存在
	 * @param account
	 * @param telphone
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/per/isExitByPerTelphone.html"  )
	public boolean isExitByPerTelphone(@SessionAttribute Account account,String telphone){
		boolean flag = securityService.isExitByPerTelphone(account.getAccountId(),telphone);
		return flag;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/per/isExitByTelcode.html"  )
	public boolean isExitByTelcode(@SessionAttribute Account account,HttpServletRequest request,String changetelcode){
		String code = String.valueOf(request.getSession().getAttribute("changetelcode"));
		if(changetelcode.equals(code)){
			return true; 
		}else{
			return false;
		}
	}
	
	
	
	/**
	 * 保存新的手机号码
	 * @param modelMap
	 * @return
	 */
	@Token(remove = true)
	@RequestMapping(value = "/per/savetelphone.do")
	public String save_telphone(@SessionAttribute Account account,String telphone,String changetelcode,HttpServletRequest request, ModelMap modelMap){
		String code = String.valueOf(request.getSession().getAttribute("changetelcode"));
		if(changetelcode.equals(code)){
			securityService.savePerTelphone(account.getAccountId(),telphone);
			return "redirect:/per/security.html"; 
		}else{
			
			return "redirect:per/changetelphone.html";
		}
		
	}
	
	
}
