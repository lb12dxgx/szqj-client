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
public interface PerCertInfoRepository extends PagingAndSortingRepository<PerCertInfo, String> {

	@Query("select m from PerCertInfo m where  m.personId=?1  order by startDate desc")
	List<PerCertInfo> findList(String personId);
	
}
