package com.szqj.service.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;




/**
 * 
 * @author lb12
 *
 */
public interface EnterpriseRepository extends PagingAndSortingRepository<Enterprise, String> {

	Page<Enterprise> findPageByEnterpriseName(String enterpriseName, Pageable pageable);
	
}
