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
public interface TrainSignUpRepository extends PagingAndSortingRepository<TrainSignUp, String> {


	@Query("select m from TrainSignUp m where m.trainPlanId = ?1  order by createDate")
	Page<TrainSignUp> findPageByTrainPlanId(String trainPlanId, Pageable pageable);
	
	@Query("select m from TrainSignUp m where  m.trainPlanId = ?1 and m.userName like %?2%   order by createDate")
	Page<TrainSignUp> findPageByTrainPlanIdAndUserName(String trainPlanId,String userName,Pageable pageable); 
	
	@Query("select m from TrainSignUp m where m.trainPlanId = ?1  order by empName ,createDate")
	List<TrainSignUp> findListByTrainPlanId(String trainPlanId);
	
}
