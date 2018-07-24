package com.szqj.website.emp.control;

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
import com.szqj.service.domain.Enterprise;
import com.szqj.service.domain.EnterpriseRepository;
import com.szqj.service.domain.MyPersonRepository;
import com.szqj.service.domain.Person;
import com.szqj.springmvc.Token;
import com.szqj.util.RestJson;
import com.szqj.weborg.domain.Account;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class EmpSecurityControle {
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private EnterpriseRepository enterpriseRepository;

	/**
	 * 个人 安全信息
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/emp/security.html"  )
	public String index_security(){
		return "emp/security"; 
	}
	
	
	/**
	 * 进入密码重新设置页面
	 * @param modelMap
	 * @return
	 */
	@Token(save = true)
	@RequestMapping(value = "/emp/changepassword.html"  )
	public String change_password(){
		return "emp/changepassword"; 
	}
	
	
	/**
	 * 验证原先密码
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/emp/repassword.html"  )
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
	@RequestMapping(value = "/emp/savepassword.do"  )
	public String save_password(@SessionAttribute Account account,String password,String oldpassword){
		boolean flag=securityService.repassword(account.getAccountId(),oldpassword);
		if(flag){
			securityService.savePassWord(account.getAccountId(),password,oldpassword);
			return "redirect:/emp/security.html"; 
		}else{
			return "emp/changepassword"; 
		}
	}
	
	
	/**
	 * 进入 手机号重新设置页面
	 * @param modelMap
	 * @return
	 */
	@Token(save = true)
	@RequestMapping(value = "/emp/changetelphone.html"  )
	public String change_emptelphone(@SessionAttribute Account account,ModelMap modelMap){
		List<Enterprise> l = enterpriseRepository.findByAccountId(account.getAccountId());
		modelMap.put("oldtelphone", l.get(0).getTelphone());
		return "emp/changetelphone"; 
	}
	
	
	/**
	 * 发送手机修改验证码
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/emp/sendsms.html")
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
	@RequestMapping(value = "/emp/isExitByEmpTelphone.html"  )
	public boolean isExitByEmpTelphone(@SessionAttribute Account account,String telphone){
		boolean flag = securityService.isExitByEmpTelphone(account.getAccountId(),telphone);
		return flag;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/emp/isExitByTelcode.html"  )
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
	@RequestMapping(value = "/emp/savetelphone.do")
	public String save_telphone(@SessionAttribute Account account,String telphone,String changetelcode,HttpServletRequest request, ModelMap modelMap){
		String code = String.valueOf(request.getSession().getAttribute("changetelcode"));
		if(changetelcode.equals(code)){
			securityService.saveEmpTelphone(account.getAccountId(),telphone);
			return "redirect:/emp/security.html"; 
		}else{
			
			return "redirect:/emp/changetelphone.html";
		}
		
	}
	
	
}
