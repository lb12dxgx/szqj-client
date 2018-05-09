package com.szqj.train.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;




/**
 * 
 * @author lb12
 *
 */
public interface TrainPlanRepository extends PagingAndSortingRepository<TrainPlan, String> {

	Page<TrainPlan> findPageByTrainPlanName(String trainPlanName, PageRequest pageable);

	
	
}
