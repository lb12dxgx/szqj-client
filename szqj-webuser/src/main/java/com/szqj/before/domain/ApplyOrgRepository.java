package com.szqj.before.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



/**
 * 
 * @author lb12
 *
 */
public interface ApplyOrgRepository extends JpaRepository<ApplyOrg, String> {
	
	@Query("select m from ApplyOrg m where  m.orgName like %?1% and m.state!=2")
	public Page<ApplyOrg> findPageByOrgName(String orgName,Pageable pageable);
	
	
	@Query("select m from ApplyOrg m where  m.state!=2")
	public List<ApplyOrg> findAllList();

	@Query("select m from ApplyOrg m where m.state!=2")
	public Page<ApplyOrg> findPage(Pageable pageable);
	
	
	
	
}
