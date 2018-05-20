package com.szqj.website.control;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.szqj.before.domain.ApplyOrg;
import com.szqj.before.domain.ApplyOrgRepository;
import com.szqj.cms.domain.ColumnInfo;
import com.szqj.cms.domain.ColumnInfoRepository;
import com.szqj.cms.domain.ContentInfo;
import com.szqj.cms.domain.ContentInfoRepository;
import com.szqj.train.domain.TrainClass;
import com.szqj.train.domain.TrainClassRepository;
import com.szqj.train.domain.TrainPlan;
import com.szqj.train.domain.TrainPlanRepository;
import com.szqj.train.domain.TrainTeacher;
import com.szqj.train.domain.TrainTeacherRepository;
import com.szqj.util.Tools;

@Controller
@RequestMapping("/")
@EnableAutoConfiguration
public class TrainControle {
	
	@Autowired
	private TrainPlanRepository trainPlanRepository;
	
	@Autowired
	private TrainTeacherRepository trainTeacherRepository;
	
	@Autowired
	private TrainClassRepository trainClassRepository;
	
	
	/**
	 * 培训首页
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/train/index.html")
	public String index_train(ModelMap modelMap){
		
		setMainTrain(modelMap);
		setTrainList(modelMap);
		setTrainTeacherList(modelMap);
		setTrainTeacherClassList(modelMap);
		
		return "train/index"; 
	}
	
	
	/**
	 * 教师列表
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/train/teacherList.html")
	public String teacherList(Integer pageNum, Integer size, ModelMap modelMap){
		PageRequest pageable=Tools.getPage(pageNum, 5);
		Page<TrainTeacher> page=trainTeacherRepository.findPage(pageable);
		modelMap.put("page", page);
		return "train/teacherList"; 
	}
	
	
	/**
	 * 课程列表
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/train/classList.html")
	public String classList(Integer pageNum, Integer size, ModelMap modelMap){
		
		PageRequest pageable=Tools.getPage(pageNum, 5);
		Page<TrainClass> page=trainClassRepository.findPage(pageable);
		modelMap.put("page", page);
		return "train/classList"; 
	}


    /**
     * 设置主要培训
     * @param modelMap
     */
	private void setMainTrain(ModelMap modelMap) {
		List<TrainPlan> l = trainPlanRepository.findMainTrain();
		if(l!=null&&l.size()!=0) {
			modelMap.put("mainTrain", l.get(0));
		}
	}
	
	/**
	 * 设置培训列表
	 * @param modelMap
	 */
	private void setTrainList(ModelMap modelMap) {
		List<TrainPlan> l = trainPlanRepository.findListTrain();
		if(l!=null&&l.size()!=0) {
			modelMap.put("trainList", l);
		}
	}
	
	/**
	 * 设置培训教师
	 * @param modelMap
	 */
	private void setTrainTeacherList(ModelMap modelMap) {
		List<TrainTeacher> l = trainTeacherRepository.findListByIndexShow();
		modelMap.put("teacherList", l);
	}
	
	
	/**
	 * 设置培训课程列表
	 * @param modelMap
	 */
	private void setTrainTeacherClassList(ModelMap modelMap) {
		TrainPlan mainTrain=(TrainPlan)modelMap.get("mainTrain");
		List<TrainClass> l = trainClassRepository.findByTrainPlanId(mainTrain.getTrainPlanId());
		modelMap.put("classList", l);
	}
	
	
	
	

}
