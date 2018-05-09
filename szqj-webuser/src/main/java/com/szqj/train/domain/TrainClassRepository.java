package com.szqj.train.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;




/**
 * 
 * @author lb12
 *
 */
public interface TrainClassRepository extends PagingAndSortingRepository<TrainClass, String> {

	Page<TrainClass> findPageByTrainClassName(String trainClassName, Pageable pageable);

	
	
}
