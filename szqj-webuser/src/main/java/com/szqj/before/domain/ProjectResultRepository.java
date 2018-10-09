package com.szqj.before.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



/**
 * 
 * @author lb12
 *
 */
@Repository
public interface ProjectResultRepository extends JpaRepository<ProjectResult, String> {
	
	@Query("select m from ProjectResult m where  m.beforeProjectId=?1 ")
	public List<ProjectResult> findByBeforeProjectId(String beforeProjectId);
	
	@Query("select m from ProjectResult m where  m.openid=?1 ")
	public List<ProjectResult> findByOpenId(String openId);
	
	
	
	
	

	
	
}
