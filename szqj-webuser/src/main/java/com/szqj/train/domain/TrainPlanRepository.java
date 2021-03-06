package com.szqj.train.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.szqj.service.domain.Meet;




/**
 * 
 * @author lb12
 *
 */
@Repository
public interface TrainPlanRepository extends PagingAndSortingRepository<TrainPlan, String> {
	
	@Query("select m from TrainPlan m where m.trainName like %?1% order by m.createDate desc ")
	Page<TrainPlan> findPageByTrainName(String trainName, Pageable pageable);

	@Query("select m from TrainPlan m   order by m.createDate  ")
	Page<TrainPlan> findPage(Pageable pageable);
	
	@Query("select m from TrainPlan m where m.showMain=1  order by m.createDate desc ")
	List<TrainPlan> findMainTrain();
	
	@Query("select m from TrainPlan m where m.showMain=0  order by m.createDate desc ")
	List<TrainPlan> findListTrain();
	
	@Query("select m from TrainPlan m where m.isSign=1 order by createDate desc")
	List<TrainPlan> findByIsSign();
}
