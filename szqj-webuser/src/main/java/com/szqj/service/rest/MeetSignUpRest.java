package com.szqj.service.rest;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.service.domain.Meet;
import com.szqj.service.domain.MeetRepository;
import com.szqj.service.domain.MeetSignUp;
import com.szqj.service.domain.MeetSignUpRepository;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;


@Controller
@RequestMapping("/system/meetsignup/")
@EnableAutoConfiguration
public class MeetSignUpRest {
	
	@Autowired
	private MeetSignUpRepository meetsignRepository;
	
	@Autowired
	private MeetRepository meetRepository;
	
	
	
	@ResponseBody
	@RequestMapping(value = "list.do"  )
	public RestJson list( String meetId, String userName,  Integer pageNum, Integer size){
		
		Page<MeetSignUp> page=null;
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		if(StringUtils.isNotBlank(userName)) {
			page =  meetsignRepository.findPageByMeetIdAndUserName(meetId,userName, pageable);
		}else {
			page = meetsignRepository.findPageByMeetId(meetId,pageable);
		}
		
		return RestJson.createSucces(page);
	}
	
	@ResponseBody
	@RequestMapping(value = "get.do"  )
	public RestJson get(String meetSignUpId){
		MeetSignUp meetsign = meetsignRepository.findById(meetSignUpId).get();
		return RestJson.createSucces(meetsign);
	}
	
	@ResponseBody
	@RequestMapping(value = "save.do"  )
	public RestJson save( MeetSignUp meetsign){
		meetsign.setCreateDate(new Date());
		meetsignRepository.save(meetsign);
		return RestJson.createSucces(meetsign);
	}
	
	@RequestMapping(value = "excel.do"  )
	public void excel(String meetId, HttpServletResponse response) throws IOException{
		Meet meet = meetRepository.findById(meetId).get();
		List<MeetSignUp> list = meetsignRepository.findListByMeetId(meetId);
		Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(meet.getMeetName(),"报名信息"),
				MeetSignUp .class, list);
		 if (workbook != null);
	     downLoadExcel("报名信息.xls", response, workbook);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "update.do"  )
	public RestJson update( MeetSignUp meetsign){
		meetsignRepository.save(meetsign);
		return RestJson.createSucces(meetsign);
	}
	
	@ResponseBody
	@RequestMapping(value = "delete.do"  )
	public RestJson delete( String meetsignId){
		meetsignRepository.deleteById(meetsignId);
		return RestJson.createSucces();
	}
	
	
	private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) throws IOException {
        
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
      
    }
	
	
	
	

}
