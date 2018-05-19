package com.szqj.train.rest;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

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

import com.szqj.train.domain.TrainPlan;
import com.szqj.train.domain.TrainPlanRepository;
import com.szqj.train.domain.TrainSignUp;
import com.szqj.train.domain.TrainSignUpRepository;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;


@Controller
@RequestMapping("/system/trainsignup/")
@EnableAutoConfiguration
public class TrainSignUpRest {
	
	@Autowired
	private TrainSignUpRepository trainSignUpRepository;
	
	@Autowired
	private TrainPlanRepository trainPlanRepository;
	
	
	
	@ResponseBody
	@RequestMapping(value = "list.do"  )
	public RestJson list( String trainPlanId, String userName,  Integer pageNum, Integer size){
		
		Page<TrainSignUp> page=null;
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		if(StringUtils.isNotBlank(userName)) {
			page =  trainSignUpRepository.findPageByTrainPlanIdAndUserName(trainPlanId,userName, pageable);
		}else {
			page = trainSignUpRepository.findPageByTrainPlanId(trainPlanId,pageable);
		}
		
		return RestJson.createSucces(page);
	}
	
	@ResponseBody
	@RequestMapping(value = "get.do"  )
	public RestJson get(String trainSignUpId){
		TrainSignUp trainSignUp = trainSignUpRepository.findById(trainSignUpId).get();
		return RestJson.createSucces(trainSignUp);
	}
	
	@ResponseBody
	@RequestMapping(value = "save.do"  )
	public RestJson save( TrainSignUp trainSignUp){
		trainSignUp.setCreateDate(new Date());
		trainSignUpRepository.save(trainSignUp);
		return RestJson.createSucces(trainSignUp);
	}
	
	@RequestMapping(value = "excel.do"  )
	public void excel(String trainPlanId, HttpServletResponse response) throws IOException{
		TrainPlan trainPlan = trainPlanRepository.findById(trainPlanId).get();
		List<TrainSignUp> list = trainSignUpRepository.findListByTrainPlanId(trainPlanId);
		Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(trainPlan.getTrainName(),"报名信息"),
				TrainSignUp .class, list);
		 if (workbook != null);
	     downLoadExcel("报名信息.xls", response, workbook);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "update.do"  )
	public RestJson update( TrainSignUp trainSignUp){
		trainSignUpRepository.save(trainSignUp);
		return RestJson.createSucces(trainSignUp);
	}
	
	@ResponseBody
	@RequestMapping(value = "delete.do"  )
	public RestJson delete( String trainSignUpId){
		trainSignUpRepository.deleteById(trainSignUpId);
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
