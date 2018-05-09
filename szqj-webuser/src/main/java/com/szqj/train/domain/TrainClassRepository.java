package com.szqj.train.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;




/**
 * 
 * @author lb12
 *
 */
public interface TrainClassRepository extends PagingAndSortingRepository<TrainClass, String> {

	Page<TrainClass> findPageByTrainClassName(String trainClassName, PageRequest pageable);

	
	
}
