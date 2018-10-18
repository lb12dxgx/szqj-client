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
public interface PersonAreaRepository extends JpaRepository<PersonArea, String> {
	
	@Query("select m from PersonArea m where  m.openid=?1 and  m.enterpriseId=?2")
	public List<PersonArea> findByEnterpriseIdAndOpenid(String openid,String enterpriseId);
	
}
