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
public interface ZbInfoRepository extends PagingAndSortingRepository<ZbInfo, String> {

	@Query("select m from ZbInfo m where m.zbXmName like %?1%  order by createDate")
	Page<ZbInfo> findPageByZbXmName(String zbXmName, Pageable pageable);
	
	@Query("select m from ZbInfo m  order by createDate desc")
	Page<ZbInfo> findPage(Pageable pageable);
	
	
	@Query("select m from ZbInfo m where m.url=?1 order by createDate")
	List<ZbInfo> findListByUrl(String url);
	
}
