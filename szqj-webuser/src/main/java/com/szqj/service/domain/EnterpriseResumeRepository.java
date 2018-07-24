package com.szqj.service.domain;

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
public interface EnterpriseResumeRepository extends PagingAndSortingRepository<EnterpriseResume, String> {


	/**
	 * 待处理
	 * @param enterpriseName
	 * @param pageable
	 * @return
	 */                                                
	@Query("select a.person from EnterpriseResume a  where a.enterprise.enterpriseId =?1 and a.state=0  order by a.createDate ")
	Page<Person> findPageaByUn(String enterpriseId, Pageable pageable);
	
	
	/**
	 * 已处理
	 * @param enterpriseName
	 * @param pageable1
	 * @return
	 */
	@Query("select a.person from EnterpriseResume a where a.enterprise.enterpriseId =?1 and (a.state=1 or a.state=2)   order by a.createDate ")
	Page<Person> findPageaByFinsh(String enterpriseId, Pageable pageable)
	
	
	
}
