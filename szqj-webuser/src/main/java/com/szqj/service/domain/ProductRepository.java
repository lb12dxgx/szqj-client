package com.szqj.service.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;




/**
 * 
 * @author lb12
 *
 */
public interface ProductRepository extends PagingAndSortingRepository<Product, String> {

	Page<Product> findPageByProductName(String productName, Pageable pageable);
	
}
