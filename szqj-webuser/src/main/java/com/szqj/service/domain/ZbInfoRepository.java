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
public interface ZbInfoRepository extends PagingAndSortingRepository<ZbInfo, String> {

	@Query("select m from ZbInfo m where m.zbXmName like %?1%  order by createDate")
	Page<ZbInfo> findPageByZbXmName(String zbXmName, Pageable pageable);
	
	@Query("select m from ZbInfo m  order by createDate")
	Page<ZbInfo> findPage(Pageable pageable);
	
}
