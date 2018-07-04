package com.szqj.train.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;




/**
 * 
 * @author lb12
 *
 */
@Repository
public interface TrainClassRepository extends PagingAndSortingRepository<TrainClass, String> {

	Page<TrainClass> findPageByTrainClassName(String trainClassName, Pageable pageable);

	List<TrainClass> findByTrainPlanId(String trainPlanId);
	
	@Query("select m from TrainClass m   order by m.createDate  ")
	Page<TrainClass> findPage(Pageable pageable);
	
}
