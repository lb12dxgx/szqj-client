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
public interface PerJobInfoRepository extends PagingAndSortingRepository<PerJobInfo, String> {

	@Query("select m from PerJobInfo m where  m.personId=?1  order by jobStartDate desc")
	List<PerJobInfo> findList(String personId);
	
}
