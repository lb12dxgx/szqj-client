package com.szqj.service.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;




/**
 * 
 * @author lb12
 *
 */
public interface EnterpriseRepository extends PagingAndSortingRepository<Enterprise, String> {


	@Query("select m from Enterprise m where m.enterpriseName like %?1%  order by createDate")
	Page<Enterprise> findPageByEnterpriseName(String enterpriseName, Pageable pageable);
	
	@Query("select m from Enterprise m  order by createDate")
	Page<Enterprise> findPage(Pageable pageable);
	
}
