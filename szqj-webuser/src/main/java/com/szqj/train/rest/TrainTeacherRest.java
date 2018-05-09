package com.szqj.train.rest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szqj.train.domain.TrainTeacher;
import com.szqj.train.domain.TrainTeacherRepository;
import com.szqj.util.RestJson;
import com.szqj.util.Tools;



@RestController
@RequestMapping("/system/trainteacher/")
@EnableAutoConfiguration
public class TrainTeacherRest {
	
	@Autowired
	private TrainTeacherRepository trainTeacherRepository;
	
	
	
	@RequestMapping(value = "list.do"  )
	
	public RestJson list( String teacherName,   Integer pageNum, Integer size){
		PageRequest pageable=Tools.getPage(pageNum-1, size);
		Page<TrainTeacher> page = trainTeacherRepository.findPageByTeacherName(teacherName, pageable);
		return RestJson.createSucces(page);
	}
	
	
	
	@RequestMapping(value = "save.do"  )
	public RestJson save( TrainTeacher trainTeacher){
		trainTeacher.setCreateDate(new Date());
		trainTeacherRepository.save(trainTeacher);
		return RestJson.createSucces(trainTeacher);
	}
	
	
	@RequestMapping(value = "update.do"  )
	public RestJson update( TrainTeacher trainTeacher){
		trainTeacherRepository.save(trainTeacher);
		return RestJson.createSucces(trainTeacher);
	}
	
	
	@RequestMapping(value = "delete.do"  )
	public RestJson delete( String trainTeacherId){
		trainTeacherRepository.deleteById(trainTeacherId);
		return RestJson.createSucces();
	}
	
	
		
	
	
	

}
