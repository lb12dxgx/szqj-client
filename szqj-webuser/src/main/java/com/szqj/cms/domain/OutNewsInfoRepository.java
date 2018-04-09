package com.szqj.cms.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;




/**
 * 
 * @author lb12
 *
 */
public interface OutNewsInfoRepository extends JpaRepository< OutNewsInfo, String> {
	
	@Query("select m from OutNewsInfo m where m.url=?1")
	public List<OutNewsInfo> findByUrl(String url);
	
}
