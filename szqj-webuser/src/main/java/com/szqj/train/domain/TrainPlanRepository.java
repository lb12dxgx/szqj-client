package com.szqj.train.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;




/**
 * 
 * @author lb12
 *
 */
public interface TrainPlanRepository extends PagingAndSortingRepository<TrainPlan, String> {
	
	@Query("select m from TrainPlan m where m.trainName=?1 order by m.createDate  ")
	Page<TrainPlan> findPageByTrainName(String trainName, Pageable pageable);

	@Query("select m from TrainPlan m   order by m.createDate  ")
	Page<TrainPlan> findPage(Pageable pageable);
	
}