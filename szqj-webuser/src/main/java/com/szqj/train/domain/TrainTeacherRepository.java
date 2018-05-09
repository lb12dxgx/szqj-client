package com.szqj.train.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;




/**
 * 
 * @author lb12
 *
 */
public interface TrainTeacherRepository extends PagingAndSortingRepository<TrainTeacher, String> {


	Page<TrainTeacher> findPageByTeacherName(String teacherName, PageRequest pageable);

	
	
}
