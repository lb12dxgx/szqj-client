package com.szqj.train.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;




/**
 * 
 * @author lb12
 *
 */
public interface TrainTeacherRepository extends PagingAndSortingRepository<TrainTeacher, String> {

	@Query("select m from TrainTeacher m where m.teacherName like %?1% order by m.createDate  ")
	Page<TrainTeacher> findPageByTeacherName(String teacherName, Pageable pageable);

	

	@Query("select m from TrainTeacher m   order by teacherCode  ")
	Page<TrainTeacher> findPage(Pageable pageable);
	
	@Query("select m from TrainTeacher m where m.indexShow=1  order by teacherCode  ")
	List<TrainTeacher> findListByIndexShow();
	
}
