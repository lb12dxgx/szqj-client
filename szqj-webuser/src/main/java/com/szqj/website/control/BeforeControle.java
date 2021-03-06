package com.szqj.website.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.szqj.PdfTools;
import com.szqj.before.domain.ApplyCity;
import com.szqj.before.domain.ApplyCityRepository;
import com.szqj.before.domain.ApplyOrg;
import com.szqj.before.domain.ApplyOrgRepository;
import com.szqj.before.domain.BeforeApply;
import com.szqj.before.domain.BeforeApplyRepository;
import com.szqj.before.service.BeforeApplyService;
import com.szqj.cms.domain.ColumnInfo;
import com.szqj.cms.domain.ColumnInfoRepository;
import com.szqj.cms.domain.ContentInfo;
import com.szqj.cms.domain.ContentInfoRepository;
import com.szqj.redis.RedisService;
import com.szqj.reg.domain.RegInfo;
import com.szqj.reg.service.RegService;
import com.szqj.springmvc.Token;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;
import com.szqj.weborg.service.AccountService;
import com.szqj.yun.SmsTools;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class BeforeControle {
	
	
	@Autowired
	private RegService regService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private BeforeApplyRepository beforeApplyRepository;
	
	@Autowired
	private BeforeApplyService beforeApplyService;
	
	@Autowired
	private ApplyOrgRepository applyOrgRepository;
	
	@Autowired
	private ColumnInfoRepository columnInfoRepository;
	@Autowired
	private ContentInfoRepository contentInfoRepository;
	@Autowired
	private ApplyCityRepository applyCityRepository;
	@Autowired
	private RedisService redisService;


	@Value("${web.upload-pdf}")
	private String pdfPath;
	
	
	/**
	 * 118服务
	 * @param modelMap
	 * @return
	 */
	@Token(save = true)
	@RequestMapping(value = "/118/index.html"  )
	public String index_118(ModelMap modelMap){
		List<ApplyOrg> l = applyOrgRepository.findAllList();
		modelMap.put("orgList", l);
		return "118/index";  
	}
	
	
	/**
	 * 118服务城市保存
	 * @param modelMap
	 * @return
	 */
	@Token(remove =true)
	@RequestMapping(value = "/118/saveCity.html"  )
	public String save_city(ApplyCity applyCity,ModelMap modelMap){
		applyCity.setCreateDate(new Date());
		applyCityRepository.save(applyCity);
		modelMap.put("applyCity", applyCity);
		return "118/indexSucess";  
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/118/city/getSmsCode.do"  )
	public RestJson getSmsCode(String telphone){
		String smscode = regService.getRandomCode(4);
		try {
			SmsTools.alSendSms(smscode,telphone);
			//redisService.putCityPhoneAndSmsCode(telphone, smscode);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		redisService.putCityPhoneAndSmsCode(telphone, smscode);
		
		return RestJson.createSucces();
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/118/city/validateSmsCode.do"  )
	public RestJson validateSmsCode(String telphone,String smscode){
		
		String tel=redisService.getSmsCodeByCityPhone(telphone);
		if(StringUtils.isBlank(tel)||!tel.equals(smscode)) {
			return RestJson.createError();
		}
		
		return RestJson.createSucces();
	}
	
	
	
	/**
	 * 118模式
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/118/model.html"  )
	public String index_model(ModelMap modelMap){
		ColumnInfo columnInfo=columnInfoRepository.findByColumnCode("2_cy_fw_118");
		List<ContentInfo> list=contentInfoRepository.findListByColumnId(columnInfo.getColumnId());
		modelMap.put("list", list);
		return "118/model"; 
	}
	
	
	/**
	 * 118模式
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/118/modeldetail.html"  )
	public String model_detail(String contentId,ModelMap modelMap){
		ContentInfo content = contentInfoRepository.findById(contentId).get();
		modelMap.put("content", content);
		return "118/modeldetail"; 
	}
	
	
	@RequestMapping(value = "/118/login.html"  )
	public String login(String applyOrgId,ModelMap modelMap){
		List<ApplyOrg> l = applyOrgRepository.findAllList();
		modelMap.put("orgList", l);
		modelMap.put("applyOrgId",applyOrgId);
		
		return "118/login";
	}
	
	@Token(save = true)
	@RequestMapping(value = "/118/reg.html"  )
	public String reg(ModelMap modelMap){
		return "118/reg";
	}
	
	
	
	@RequestMapping(value = "/118/search.html"  )
	public String search(ModelMap modelMap){
		return "118/search";
	}
	
	
	@RequestMapping(value = "/118/resultSucess.html"  )
	public String resultSucess(ModelMap modelMap){
		return "118/resultSucess";
	}
	
	@RequestMapping(value = "/118/resultFail.html"  )
	public String resultFail(ModelMap modelMap){
		return "118/resultFail";
	}
	
	
	
	@RequestMapping(value = "/118/about.html"  )
	public String about(ModelMap modelMap){
		return "118/about";
	}
	
	@ResponseBody
	@RequestMapping(value = "/118/getOrgList.do"  )
	public RestJson getOrgList(){
		List<ApplyOrg> l = applyOrgRepository.findAllList();
		return RestJson.createSucces(l);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/118/getSmsCode.do"  )
	public RestJson getSmsCode(RegInfo regInfo){
		RegInfo regInfoRet = regService.genSmsCode(regInfo,4);
		return RestJson.createSucces(regInfoRet.getSmscode());
	}
	
	/*@Token(save = true)
	@RequestMapping(value = "/118/login.do"  )
	public String login(String userName ,String password,String applyOrgId,ModelMap modelMap,RedirectAttributes attributes){
		Account account = accountService.login(userName, password);
		account.setToken(account.getAccountId());
		if(StringUtils.isBlank(account.getLoginStr())){
			
			 ApplyOrg applyOrg = applyOrgRepository.findById(applyOrgId).get();
			 Company company = companyRepository.findByAccountId(account.getAccountId());
			 
			 modelMap.put("account", account);
			 modelMap.put("applyOrgId", applyOrgId);
			 modelMap.put("companyId",company.getCompanyId());
			 if(("com").equals(applyOrg.getFormCode())) {
				 return "118/submitTwo";
			 }else {
				 return "118/submitTwo"+applyOrg.getFormCode();
			 }
		}else{
			 modelMap.put("loginStr", account.getLoginStr());
			 modelMap.put("applyOrgId", applyOrgId);
			 return "118/login";
		}
		
	}*/
	
	
	@ResponseBody
	@RequestMapping(value = "/118/validateSmsCode.do"  )
	public RestJson validateSmsCode(RegInfo regInfo){
		boolean flag = regService.validateSmsCode(regInfo);
		return RestJson.createSucces(!flag);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/118/isExitByTelphone.do"  )
	public boolean isExitByTelphone(RegInfo regInfo){
		boolean flag = regService.isExitByTelphone(regInfo);
		return !flag;
	}
	
	@Token(remove =true,save = true)
	@RequestMapping(value = "/118/regSubmit.do"  )
	public String regSubmit(RegInfo regInfo,ModelMap modelMap){
		RegInfo reg=regService.regBeforeUser(regInfo);
		modelMap.put("regInfo", reg);
		return "118/regTwo";
	}
	
	/*@Token(remove =true)
	@RequestMapping(value = "/118/regComapnySubmit.do"  )
	public String regComapnySubmit(String reginfoId,Company company,ModelMap modelMap){
		RegInfo reg=regService.regBeforeComapnySubmit(reginfoId,company);
		modelMap.put("regInfo", reg);
		return "118/login";
	}
	
	@Token(remove =true)
	@RequestMapping(value = "/118/applySubmit.do"  )
	public String applySubmit(BeforeApply beforeApply,ModelMap modelMap){
		beforeApplyService.create(beforeApply);
		ApplyOrg applyOrg = applyOrgRepository.findById(beforeApply.getApplyOrgId()).get();
		String companyId = beforeApply.getCompanyId();
		Company company = companyRepository.findById(companyId).get();
		modelMap.put("beforeApply", beforeApply);
		modelMap.put("applyOrg", applyOrg);
		modelMap.put("company", company);
		
		return "118/submitThree";
	}*/
	

	@RequestMapping(value = "/downloadpdf")
    public void pdfStreamHandler(String beforeApplyId,HttpServletResponse response) {

		try {
				BeforeApply beforeApply=beforeApplyRepository.findById(beforeApplyId).get();
				FileInputStream input = new FileInputStream(genApplyPdf( beforeApply));
                byte[] data = new byte[input.available()];
                input.read(data);
                response.getOutputStream().write(data);
                input.close();
            } catch (Exception e) {
            	System.out.println(e.getMessage());
            }
	}
	
	
	private File genApplyPdf(BeforeApply beforeApply) throws FileNotFoundException {
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd"); 
		String startDateStr = simpleDateFormat.format(beforeApply.getStartDate());
		
		String fileName=beforeApply.getApplyCode();
		HashMap<String,String> hashMap=new HashMap<String,String>();
		
		hashMap.put("street", beforeApply.getStreet());
		hashMap.put("crossroads", beforeApply.getCrossroads());
		hashMap.put("identification", beforeApply.getIdentification());
		hashMap.put("tools", beforeApply.getTools());
		hashMap.put("startDate", startDateStr);
		hashMap.put("code", beforeApply.getCode());
		hashMap.put("party", beforeApply.getParty());
		
		ApplyOrg applyOrg = applyOrgRepository.findById(beforeApply.getApplyOrgId()).get();
		
		hashMap.put("orgName", applyOrg.getOrgName());
		hashMap.put("telphone", applyOrg.getTelphone());
		hashMap.put("address", applyOrg.getAddress());
		hashMap.put("workDate", applyOrg.getWorkDate());
		
		File file = new File("d://temp"+ fileName+".pdf");
	        // 检测是否存在目录
	        if (!file.getParentFile().exists()) {
	        	file.getParentFile().mkdirs();
	        }
		File pdfForm = ResourceUtils.getFile("classpath:pdf/811申请单.pdf");
		
		PdfTools.genPdf(file, pdfForm, hashMap);
		
		return file;
		
	}
	
	

	

}
