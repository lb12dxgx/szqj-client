package com.szqj.company.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;




/**
 * 
 * @author lb12
 *
 */
public interface CompanyRepository extends PagingAndSortingRepository<Company, String> {

	Page<Company> findPageByName(String name, Pageable pageable);
	
	
	
	
	
	
}
