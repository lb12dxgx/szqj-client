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
public interface ZbGlInfoRepository extends PagingAndSortingRepository<ZbGlInfo, String> {

	@Query("select m from ZbGlInfo m where m.zbXmName like %?1%  order by createDate")
	Page<ZbGlInfo> findPageByZbXmName(String zbXmName, Pageable pageable);
	
	@Query("select m from ZbGlInfo m  order by createDate desc")
	Page<ZbGlInfo> findPage(Pageable pageable);
	
	
	@Query("select m from ZbGlInfo m where m.url=?1 order by createDate")
	List<ZbGlInfo> findListByUrl(String url);
	
}
