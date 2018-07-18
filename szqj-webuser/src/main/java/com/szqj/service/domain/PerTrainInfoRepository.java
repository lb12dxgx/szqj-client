package com.szqj.service.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;




/**
 * 
 * @author lb12
 *
 */
@Repository
public interface PerTrainInfoRepository extends PagingAndSortingRepository<PerTrainInfo, String> {

	@Query("select m from PerTrainInfo m where  m.personId=?1  order by startDate desc")
	List<PerTrainInfo> findList(String personId);
	
}
