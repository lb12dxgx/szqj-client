package com.szqj.service.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;




/**
 * 
 * @author lb12
 *
 */
public interface EnterpriseRepository extends PagingAndSortingRepository<Enterprise, String> {

	Page<Enterprise> findPageByEnterpriseName(String enterpriseName, PageRequest pageable);
	
}
