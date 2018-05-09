package com.szqj.train.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;




/**
 * 
 * @author lb12
 *
 */
public interface TrainPlanRepository extends PagingAndSortingRepository<TrainPlan, String> {

	Page<TrainPlan> findPageByTrainName(String trainName, Pageable pageable);

	
	
}
