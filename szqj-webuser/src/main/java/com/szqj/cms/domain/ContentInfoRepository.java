package com.szqj.cms.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


/**
 * 
 * @author lb12
 *
 */
public interface ContentInfoRepository extends JpaRepository<ContentInfo, String> {
	
	public Page<ContentInfo> findByColumnId(String columnId,Pageable pageable);
	
	@Query("select m from ContentInfo m where m.columnId =?1 and m.contentTitle like %?2% order by code")
	public Page<ContentInfo> findByColumnId(String columnId,String contentTitle, Pageable pageable);
}
