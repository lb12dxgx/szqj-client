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
public interface BeforeProjectRepository extends JpaRepository<BeforeProject, String> {
	
	@Query("select m from BeforeProject m where  m.applyCityId=?1 ")
	public List<BeforeProject> findByApplyCityId(String applyCityId);
	
	@Query("select m from BeforeProject m where  m.openid=?1 ")
	public List<BeforeProject> findByApplyOpenId(String openId);
	
	
	
	
	

	
	
}
