package com.szqj.service.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;




/**
 * 
 * @author lb12
 *
 */
public interface ZbInfoRepository extends PagingAndSortingRepository<ZbInfo, String> {

	Page<ZbInfo> findPageByZbXmName(String zbXmName, Pageable pageable);
	
}
