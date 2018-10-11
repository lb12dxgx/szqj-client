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
	
	@Query("select m from BeforeProject m where  m.applyCityId=?1 and not exists (select r from ProjectResult r where r.beforeProject=m and r.enterpriseId=?2)  and m.enterpriseId!=?2")
	public List<BeforeProject> findByApplyCityIdAndEnterpriseId(String applyCityId,String enterpriseId);
	
	@Query("select m from BeforeProject m   where  m.openid=?1 ")
	public List<BeforeProject> findByApplyOpenId(String openId);
	
	
	@Query("select m from ProjectResult r  right join r.beforeProject m  where  r.enterpriseId=?1 or m.enterpriseId=?1") 
	public List<BeforeProject> findByFinshApplyEnterpriseId(String enterpriseId);
	
	
	
	
	

	
	
}
