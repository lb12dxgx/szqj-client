package com.szqj.service.domain;

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
public interface ProductRepository extends PagingAndSortingRepository<Product, String> {

	
	
	@Query("select m from Product m where m.productName like %?1%  order by createDate")
	Page<Product> findPageByProductName(String productName, Pageable pageable);
	
	@Query("select m from ZbInfo m  order by createDate")
	Page<Product> findPage(Pageable pageable);
	
}
